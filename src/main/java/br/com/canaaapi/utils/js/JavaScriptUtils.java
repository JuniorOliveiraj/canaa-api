package br.com.canaaapi.utils.js;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;



public final class JavaScriptUtils {

	private static final long TEMPO_EM_SEGUNDOS = 3;
/*
	private static final Object eval(String js, Map<String, Object> map) throws Exception {
		final ManagedExecutorService executorService = CDI.current().select(ManagedExecutorService.class).get();
		Future<Object> future = executorService.submit(new CallableScript(js, map));
		try {
			return future.get(TEMPO_EM_SEGUNDOS, TimeUnit.SECONDS);
		} catch (TimeoutException | InterruptedException | ExecutionException ex) {
			future.cancel(true);
			throw ex;
		} finally {
			// executorService.shutdown();
			// CDI.current().destroy(executorService);
		}
	}

	public static final Double evalDouble(String js, Map<String, Object> map) throws Exception {
		double n = Context.toNumber(eval(js, map));
		if (Double.isNaN(n) || n < -999_999_999_999.9999D || n > 999_999_999_999.9999D) {
			return null;
		}
		return BigDecimal.valueOf(n).setScale(4, RoundingMode.DOWN).doubleValue();
	}

	public static final Double evalDouble6Decimais(String js, Map<String, Object> map) throws Exception {
		double n = Context.toNumber(eval(js, map));
		if (Double.isNaN(n) || n < -9_999_999_999.99999999999999999999D || n > 9_999_999_999.99999999999999999999D) { // será arredondado abaixo
			return null;
		}
		return BigDecimal.valueOf(n).setScale(6, RoundingMode.DOWN).doubleValue();
	}*/
}