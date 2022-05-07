package com.example.GLR;

public class GLR {
    public static void GLRsingle(String imgPath, double sf){
        double proportion = RGB2HLS.GetRGB(imgPath,sf);
        System.out.println("The green visual rate is:"+proportion*100+"%");

    }
    public static void main(String[] args) {
        GLRsingle("C:\\Users\\Obama\\IdeaProjects\\demo\\src\\main\\java\\com\\example\\GLR\\inputImg\\greenTest1.png",0.2);
    }
}
