package com.example.GLR;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class RGB2HLS {
    static class HLS {
        float H, L, S;
        /**
         * 定义HLS返回类
         * @param H h
         * @param L l
         * @param S s
         */
        public HLS(float H, float L, float S) {
            this.H = H;
            this.L = L;
            this.S = S;
        }
    }
    /**
     * 获取图片RGB
     * @param imgPath 文件路径
     * @param sf 压缩率
     * @return
     */
    public static double GetRGB(String imgPath, double sf){
        BufferedImage bufferedImage;
        int r,g,b;
        int height,width;
        int gg = 0, rr= 0;
        bufferedImage= ImageUtil.reSize(new File(imgPath),sf,sf);
        assert bufferedImage != null;
        height = bufferedImage.getHeight();
        width = bufferedImage.getWidth();
        for (int y = 0; y<height;y++){
            for (int x = 0; x<width;x++){
                Color color = new Color(bufferedImage.getRGB(x,y));
                r=color.getRed();
                g = color.getGreen();
                b = color.getBlue();
                HLS hls = rgb2Hls(r,g,b);
                gg +=GetGreen(hls);
                rr++;
            }
        }
        return (float) gg / (float) rr;
    }

    /**
     * RGB2HLS
     *
     * @param r r
     * @param g g
     * @param b b
     * @return
     */
    public static HLS rgb2Hls(int r, int g, int b) {
        float dr = (float)r/(float)255;
        float dg = (float)g/(float)255;
        float db = (float)b/(float)255;
        float cmax = Math.max((Math.max(dr, dg)), db);
        float cmin = Math.min((Math.min(dr, dg)), db);
        float cdes = cmax - cmin;
        float hh, ll, ss;
        ll = (cmax+cmin)/(float)2;
        if(cdes>0){
            if(ll <= 0.5)
                ss = (cmax-cmin)/(cmax+cmin);
            else
                ss = (cmax-cmin)/(2-cmax-cmin);
            if (ll==0)
                ss =0;
            if(cmax == dr) {
                hh = (0 + (dg - db) /  cdes) * 60;
                ss = 0;
            }else if(cmax == dg)
                hh = (2+(db-dr)/cdes)*60;
            else
                hh = (4+(dr-dg)/cdes)*60;

            if(hh<0)
                hh+=360;
        }else {
            hh =ss = 0;
        }
        return new HLS(hh, ll, ss);
    }

    /**
     * 获取绿视率
     * @param hls hls
     * @return
     */
    public static int GetGreen(HLS hls){
        float hh = hls.H;
        float ss = hls.S;
        float ll = hls.L;

        if ((57 <= hh && hh <= 152) && (0.13 <= ss && ss <=1) && (0.1 <= ll && ll <= 0.9)){
            return 1;
        }else
            return 0;
    }
}
