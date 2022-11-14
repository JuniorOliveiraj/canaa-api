package br.com.canaaapi.utils;



import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Transparency;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;



public final class ImagemUtils {

	public static BufferedImage compress(BufferedImage src, float quality) throws Exception {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		ImageOutputStream ios = ImageIO.createImageOutputStream(os);
		ImageWriter writer = (ImageWriter) ImageIO.getImageWritersByFormatName("jpg").next();
		writer.setOutput(ios);
		ImageWriteParam param = writer.getDefaultWriteParam();
		if (param.canWriteCompressed()) {
			param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
			param.setCompressionQuality(quality);
		}
		writer.write(null, new IIOImage(src, null, null), param);
		writer.dispose();
		os.flush();
		ios.close();
		ByteArrayInputStream in = new ByteArrayInputStream(os.toByteArray());
		os.close();
		BufferedImage img = ImageIO.read(in);
		in.close();
		return img;
	}

	public static BufferedImage ratio(BufferedImage src, int w, int h) {
		int original_width = src.getWidth();
		int original_height = src.getHeight();
		int bound_width = w;
		int bound_height = h;
		int new_width = original_width;
		int new_height = original_height;
		// first check if we need to scale width
		if (original_width > bound_width) {
			// scale width to fit
			new_width = bound_width;
			// scale height to maintain aspect ratio
			new_height = (new_width * original_height) / original_width;
		}
		// then check if we need to scale even with the new height
		if (new_height > bound_height) {
			// scale height to fit instead
			new_height = bound_height;
			// scale width to maintain aspect ratio
			new_width = (new_height * original_width) / original_height;
		}
		BufferedImage resizedImg = new BufferedImage(new_width, new_height, src.getTransparency() == Transparency.OPAQUE ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_3BYTE_BGR);
		Graphics2D g2 = resizedImg.createGraphics();
		g2.setBackground(Color.WHITE);
		g2.clearRect(0, 0, w, h);
		g2.drawImage(src.getScaledInstance(new_width, new_height, BufferedImage.SCALE_SMOOTH), 0, 0, null);
		g2.dispose();
		return resizedImg;
	}

	public static BufferedImage scale(BufferedImage src, int w, int h) {
		BufferedImage ratio = ratio(src, w, h);
		BufferedImage resizedImg = new BufferedImage(w, h, ratio.getTransparency() == Transparency.OPAQUE ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_3BYTE_BGR);
		Graphics2D g2 = resizedImg.createGraphics();
		g2.setBackground(Color.WHITE);
		g2.clearRect(0, 0, w, h);
		g2.drawImage(ratio, (w - ratio.getWidth()) / 2, (h - ratio.getHeight()) / 2, null);
		g2.dispose();
		return resizedImg;
	}
/*
	public static BufferedImage fixOrientation(ByteArrayInputStream in) throws Exception {
		BufferedImage img = ImageIO.read(in);
		int orientation = 1;
		try {
			in.reset();
			Metadata metadata = ImageMetadataReader.readMetadata(in);
			ExifIFD0Directory exifIFD0Directory = metadata.getFirstDirectoryOfType(ExifIFD0Directory.class);
			orientation = exifIFD0Directory.getInt(ExifIFD0Directory.TAG_ORIENTATION);
		} catch (Exception ex) {
		}
		if (orientation == 1) {
			return img;
		}
		AffineTransform affineTransform = new AffineTransform();
		switch (orientation) {
			case 2: // Flip X
				affineTransform.scale(-1.0, 1.0);
				affineTransform.translate(-img.getWidth(), 0);
				break;
			case 3: // PI rotation
				affineTransform.translate(img.getWidth(), img.getHeight());
				affineTransform.rotate(Math.PI);
				break;
			case 4: // Flip Y
				affineTransform.scale(1.0, -1.0);
				affineTransform.translate(0, -img.getHeight());
				break;
			case 5: // -PI/2 and Flip X
				affineTransform.rotate(-Math.PI / 2);
				affineTransform.scale(-1.0, 1.0);
				break;
			case 6: // PI/2 and -width
				affineTransform.translate(img.getHeight(), 0);
				affineTransform.rotate(Math.PI / 2);
				break;
			case 7: // PI/2 and Flip
				affineTransform.scale(-1.0, 1.0);
				affineTransform.translate(-img.getHeight(), 0);
				affineTransform.translate(0, img.getWidth());
				affineTransform.rotate(3 * Math.PI / 2);
				break;
			case 8: // PI / 2
				affineTransform.translate(0, img.getWidth());
				affineTransform.rotate(3 * Math.PI / 2);
				break;
		}
		AffineTransformOp affineTransformOp = new AffineTransformOp(affineTransform, AffineTransformOp.TYPE_BILINEAR);
		BufferedImage fix = new BufferedImage(img.getHeight(), img.getWidth(), img.getType());
		fix = affineTransformOp.filter(img, fix);
		return fix;
	}*/
}