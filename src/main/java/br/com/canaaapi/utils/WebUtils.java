package br.com.canaaapi.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.Normalizer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonInclude.Value;
import com.fasterxml.jackson.core.JsonGenerator.Feature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.util.ArrayList;


public final class WebUtils {

	private static final Logger log4j = LogManager.getLogger(WebUtils.class);
	private static final Properties properties = new Properties();
	static {
		try {
			properties.load(new InputStreamReader(WebUtils.class.getResourceAsStream("/app.properties"), Charset.defaultCharset()));
		} catch (Exception ex) {
			log4j.error("problemas ao inicializar 'app.properties'", ex);
		}
	}
	private static final ObjectMapper objectMapper = new ObjectMapper();
	static {
		objectMapper.registerModule(new JavaTimeModule());
		objectMapper.setSerializationInclusion(Include.NON_NULL);
		objectMapper.configOverride(Map.class).setInclude(Value.construct(Include.NON_NULL, Include.NON_NULL));
		objectMapper.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES);
		objectMapper.enable(MapperFeature.ALLOW_COERCION_OF_SCALARS);
		objectMapper.enable(Feature.WRITE_BIGDECIMAL_AS_PLAIN);
		objectMapper.enable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS);
		objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		// objectMapper.configOverride(java.time.LocalDateTime.class).setFormat(com.fasterxml.jackson.annotation.JsonFormat.Value.forPattern("yyyy-MM-dd HH:mm:ss"));
	}
	private static final Pattern ipv4Pattern = Pattern.compile("^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){2}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$", Pattern.CASE_INSENSITIVE);
	private static final Pattern ipv6Pattern = Pattern.compile("^([0-9a-f]{1,4}:)([0-9a-f]{1,4}:){6}([0-9a-f]{1,4})$", Pattern.CASE_INSENSITIVE);
	private static final Pattern hexadecimalColorPattern = Pattern.compile("[a-fA-F0-9]{6}", Pattern.CASE_INSENSITIVE);
	private static final Pattern rgbColorPattern = Pattern.compile("\\s*\\((\\s*(\\d{1,2}|[01]\\d\\d|2(?:[0-4]\\d|5[0-5]))\\s*,){2}(\\s*(\\d{1,2}|[01]\\d\\d|2(?:[0-4]\\d|5[0-5]))\\s*){1}\\)\\s*$", Pattern.CASE_INSENSITIVE);
	private static final Pattern imageExtensionPattern = Pattern.compile(".+(\\.(jpe?g|png))$", Pattern.CASE_INSENSITIVE);
	private static final Pattern pdfExtensionPattern = Pattern.compile(".+(\\.(pdf))$", Pattern.CASE_INSENSITIVE);
	private static final Pattern imageOrPdfExtensionPattern = Pattern.compile(".+(\\.(jpe?g|png|pdf))$", Pattern.CASE_INSENSITIVE);

	private WebUtils() {
	}

	public static String get(String key) {
		return properties.getProperty(key);
	}

	public static Properties getProperties() {
		return properties;
	}

	public static ObjectMapper getObjectMapper() {
		return objectMapper;
	}

	public static boolean isEmpty(final Object object) {
		if (object == null) {
			return true;
		}
		if (object instanceof String) {
			return ((String) object).replaceAll("\\s", "").length() == 0;
		}
		if (object instanceof byte[]) {
			return ((byte[]) object).length == 0;
		}
		if (object instanceof short[]) {
			return ((short[]) object).length == 0;
		}
		if (object instanceof int[]) {
			return ((int[]) object).length == 0;
		}
		if (object instanceof long[]) {
			return ((long[]) object).length == 0;
		}
		if (object instanceof float[]) {
			return ((float[]) object).length == 0;
		}
		if (object instanceof double[]) {
			return ((double[]) object).length == 0;
		}
		if (object instanceof boolean[]) {
			return ((boolean[]) object).length == 0;
		}
		if (object instanceof char[]) {
			return ((char[]) object).length == 0;
		}
		if (object instanceof Object[]) {
			return ((Object[]) object).length == 0;
		}
		if (object instanceof Collection) {
			return ((Collection<?>) object).isEmpty();
		}
		if (object instanceof Map) {
			return ((Map<?, ?>) object).isEmpty();
		}
		return false;
	}

	public static boolean isValidCNPJ(final Long cnpj) {
		if (isEmpty(cnpj)) {
			return false;
		}
		DecimalFormat df = new DecimalFormat("00000000000000");
		String _cnpj = df.format(cnpj);
		df = null;
		return isValidCNPJ(_cnpj);
	}

	public static boolean isValidCNPJ(final String cnpj) {
		if (cnpj == null || cnpj.length() != 14 || !cnpj.matches("[0-9]{14}") || cnpj.equals("00000000000000") || cnpj.equals("11111111111111") || cnpj.equals("22222222222222") || cnpj.equals("33333333333333") || cnpj.equals("44444444444444") || cnpj.equals("55555555555555") || cnpj.equals("66666666666666") || cnpj.equals("77777777777777") || cnpj.equals("88888888888888") || cnpj.equals("99999999999999")) {
			return false;
		}
		final int[] pesoCNPJ = { 6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };
		Integer digito1 = calcularDigito(cnpj.substring(0, 12), pesoCNPJ);
		Integer digito2 = calcularDigito(cnpj.substring(0, 12) + digito1, pesoCNPJ);
		return cnpj.equals(cnpj.substring(0, 12) + digito1.toString() + digito2.toString());
	}

	public static boolean isValidCPF(final Long cpf) {
		if (isEmpty(cpf)) {
			return false;
		}
		DecimalFormat df = new DecimalFormat("00000000000");
		String _cpf = df.format(cpf);
		df = null;
		return isValidCPF(_cpf);
	}

	public static boolean isValidCPF(final String cpf) {
		if (cpf == null || cpf.length() != 11 || !cpf.matches("[0-9]{11}") || cpf.equals("00000000000") || cpf.equals("11111111111") || cpf.equals("22222222222") || cpf.equals("33333333333") || cpf.equals("44444444444") || cpf.equals("55555555555") || cpf.equals("66666666666") || cpf.equals("77777777777") || cpf.equals("88888888888") || cpf.equals("99999999999")) {
			return false;
		}
		final int[] pesoCPF = { 11, 10, 9, 8, 7, 6, 5, 4, 3, 2 };
		Integer digito1 = calcularDigito(cpf.substring(0, 9), pesoCPF);
		Integer digito2 = calcularDigito(cpf.substring(0, 9) + digito1, pesoCPF);
		return cpf.equals(cpf.substring(0, 9) + digito1.toString() + digito2.toString());
	}

	private static int calcularDigito(final String str, final int[] peso) {
		int soma = 0;
		for (int indice = str.length() - 1, digito; indice >= 0; indice--) {
			digito = Integer.parseInt(str.substring(indice, indice + 1));
			soma += digito * peso[peso.length - str.length() + indice];
		}
		soma = 11 - soma % 11;
		return soma > 9 ? 0 : soma;
	}

	public static final boolean isValidEmail(final String email) {
		if (isEmpty(email)) {
			return false;
		}
		return email.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
	}

	public static Long getLastInsertID(final Connection connection) throws Exception {
		Long id = null;
		PreparedStatement stmt = connection.prepareStatement("select last_insert_id()");
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			id = rs.getLong(1);
			if (rs.wasNull()) {
				id = null;
			}
		}
		rs.close();
		rs = null;
		stmt.close();
		stmt = null;
		if (id == null) {
			throw new Exception("nao foi possivel recuperar [LAST INSERT ID]");
		}
		return id;
	}

	public static Predicate<String> getBooleanPredicate() {
		return s -> {
			try {
				Boolean.valueOf(s);
				return true;
			} catch (Exception ex) {
				return false;
			}
		};
	}

	public static Predicate<String> getBytePredicate() {
		return s -> {
			try {
				Byte.valueOf(s);
				return true;
			} catch (Exception ex) {
				return false;
			}
		};
	}

	public static Predicate<String> getShortPredicate() {
		return s -> {
			try {
				Short.valueOf(s);
				return true;
			} catch (Exception ex) {
				return false;
			}
		};
	}

	public static Predicate<String> getIntegerPredicate() {
		return s -> {
			try {
				Integer.valueOf(s);
				return true;
			} catch (Exception ex) {
				return false;
			}
		};
	}

	public static Predicate<String> getLongPredicate() {
		return s -> {
			try {
				Long.valueOf(s);
				return true;
			} catch (Exception ex) {
				return false;
			}
		};
	}

	public static Predicate<String> getLocalDatePredicate() {
		return s -> {
			try {
				LocalDate.parse(s, DateTimeFormatter.ISO_LOCAL_DATE);
				return true;
			} catch (Exception ex) {
				return false;
			}
		};
	}

	public static List<Integer> getIntegerList(List<? extends Object> list) {
		return list.stream().map(String::valueOf).filter(getIntegerPredicate()).map(Integer::valueOf).collect(Collectors.toList());
	}

	public static List<Long> getLongList(List<? extends Object> list) {
		return list.stream().map(String::valueOf).filter(getLongPredicate()).map(Long::valueOf).collect(Collectors.toList());
	}

	public static List<String> getStringList(List<? extends Object> list) {
		return list.stream().filter(Objects::nonNull).map(String::valueOf).distinct().collect(Collectors.toList());
	}

	public static List<LocalDate> getLocalDateList(List<? extends Object> list) {
		return list.stream().map(String::valueOf).filter(getLocalDatePredicate()).map(s -> LocalDate.parse(s, DateTimeFormatter.ISO_LOCAL_DATE)).collect(Collectors.toList());
	}

	public static void copy(final InputStream in, final OutputStream out) throws IOException {
		final int n = 4096;
		final byte[] b = new byte[n];
		for (int r = -1; (r = in.read(b, 0, n)) != -1; out.write(b, 0, r)) {
			out.flush();
		}
	}

	public static final String removeAccent(final String value) {
		return Normalizer.normalize(value, Normalizer.Form.NFKD).replaceAll("[^\\p{ASCII}]", "");
	}

	public static final String normalize(final String value) {
		return Normalizer.normalize(value, Normalizer.Form.NFKD);
	}

	public static final String normalizeFileName(final String value) {
		return removeAccent(value).replaceAll("[^a-zA-Z0-9_\\.]", "_").toLowerCase();
	}

	public synchronized static final String shortUUID() {
		return UUID.randomUUID().toString().substring(0, 6);
	}

	public synchronized static final String uuid() {
		return UUID.randomUUID().toString();
	}

	public static final String getHost(HttpServletRequest request) {
		return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	}

	public static boolean isValidIP(final String ipAddress) {
		return ipv4Pattern.matcher(ipAddress).matches() || ipv6Pattern.matcher(ipAddress).matches();
	}

	public static boolean isValidHexadecimalColorPattern(final String hexadecimalColor) {
		return hexadecimalColorPattern.matcher(hexadecimalColor).matches();
	}

	public static boolean isValidRGBColorPattern(final String rgbColor) {
		return rgbColorPattern.matcher(rgbColor).matches();
	}

	public static boolean isValidImageExtensionPattern(final String extension) {
		return imageExtensionPattern.matcher(extension).matches();
	}

	public static boolean isValidPdfExtensionPattern(final String extension) {
		return pdfExtensionPattern.matcher(extension).matches();
	}

	public static boolean isValidImageOrPdfExtensionPattern(final String extension) {
		return imageOrPdfExtensionPattern.matcher(extension).matches();
	}

	public static List<String> getFullTextValidWords(String s) {
		String v = s.replaceAll("[\\+\\-@\\>\\<\\(\\)~\\*\"]", "").replaceAll("[\\./]", " ");
		List<String> list = new ArrayList<>(Arrays.asList(v.split(" ")));
		list.removeIf(WebUtils::isEmpty);
		return list;
	}

	public static boolean isNumeric(String s) {
		return s.matches("-?\\d+(\\.\\d+)?"); // Valida número com possível '-' e/ou casa decimal.
	}
}