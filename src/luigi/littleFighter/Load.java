package luigi.littleFighter;

import java.io.File;

import javax.imageio.ImageIO;

import java.awt.Image;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.*;
import java.awt.Toolkit;

public class Load {


    public static void main(String[] args) throws Exception {

        // File in = new File("res\\bandit_0_mirror.bmp");
        // BufferedImage source = ImageIO.read(in);

        // int color = source.getRGB(0, 0);

        // Image image = makeColorTransparent(source, new Color(color));

        // BufferedImage transparent = imageToBufferedImage(image);

        // File out = new File("res\\bandit_0_mirror.png");
        // ImageIO.write(transparent, "PNG", out);
    }

    private static BufferedImage imageToBufferedImage(Image image) {

        BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = bufferedImage.createGraphics();
        g2.drawImage(image, 0, 0, null);
        g2.dispose();

        return bufferedImage;

    }

    public static Image makeColorTransparent(BufferedImage im, final Color color) {
        ImageFilter filter = new RGBImageFilter() {

            // the color we are looking for... Alpha bits are set to opaque
            public int markerRGB = color.getRGB() | 0xFF000000;

            public final int filterRGB(int x, int y, int rgb) {
                if ((rgb | 0xFF000000) == markerRGB) {
                    // Mark the alpha bits as zero - transparent
                    return 0x00FFFFFF & rgb;
                } else {
                    // nothing to do
                    return rgb;
                }
            }
        };

        ImageProducer ip = new FilteredImageSource(im.getSource(), filter);
        return Toolkit.getDefaultToolkit().createImage(ip);
    }
        
}