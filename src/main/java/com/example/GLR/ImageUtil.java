package com.example.GLR;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageUtil {
    /**
     * @param srcImg  原图片
     * @param sx      缩放率
     * @param sy      缩放率
     * @return
     */
    public static BufferedImage reSize(File srcImg, double sx, double sy) {
        String type = getImageType(srcImg);
        if (type == null || sx < 0 || sy < 0) {
            return null;
        }
        BufferedImage srcImage;
        try {
            srcImage = ImageIO.read(srcImg);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        // targetW，targetH分别表示目标长和宽
        BufferedImage target;
        double width =  sx * srcImage.getWidth();
        double height =  sy * srcImage.getHeight();
        ColorModel cm = srcImage.getColorModel();
        WritableRaster raster = cm.createCompatibleWritableRaster((int) width, (int) height);
        boolean alphaPremultiplied = cm.isAlphaPremultiplied();

        target = new BufferedImage(cm, raster, alphaPremultiplied, null);
        Graphics2D g = target.createGraphics();
        // smoother than exlax:
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.drawRenderedImage(srcImage, AffineTransform.getScaleInstance(sx, sy));
        g.dispose();
        return target;
    }

    /**
     * 获取文件后缀不带.
     *
     * @param file 文件后缀名
     * @return
     */
    private static String getImageType(File file) {
        if (file != null && file.exists() && file.isFile()) {
            String fileName = file.getName();
            int index = fileName.lastIndexOf(".");
            if (index != -1 && index < fileName.length() - 1) {
                return fileName.substring(index + 1);
            }
        }
        return null;
    }
}
