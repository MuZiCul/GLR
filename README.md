# GLR - Green Leaf Ratio Analyzer / GLR - 绿叶比率分析器

This Java application analyzes images to calculate the "green visual rate" - the percentage of pixels in an image that fall within a specified green color range in HLS (Hue, Lightness, Saturation) color space.
此Java应用程序通过分析图片中HLS（色相、亮度、饱和度）颜色空间内指定绿色范围的像素百分比来计算"绿色视率"。

## Technical Approach / 技术路线

1. **Image Input**: Reads an image file from the specified path
   **图像输入**：从指定路径读取图像文件
2. **Preprocessing**: Optionally resizes the image using a scaling factor for performance
   **预处理**：可选地使用缩放因子调整图像大小以提高性能
3. **Color Conversion**: Converts each pixel from RGB to HLS color space using a custom implementation
   **色彩转换**：使用自定义实现将每个像素从RGB转换为HLS色彩空间
4. **Green Detection**: Identifies green pixels based on HLS thresholds:
   **绿色检测**：基于HLS阈值识别绿色像素：
   - Hue (H): 52-152 (green range in HLS)
     色调(H)：52-152（HLS中的绿色范围）
   - Saturation (S): 0.13-1.0
     饱和度(S)：0.13-1.0
   - Lightness (L): 0.1-0.9
     亮度(L)：0.1-0.9
5. **Calculation**: Computes the ratio of green pixels to total pixels as a percentage
   **计算**：计算绿色像素与总像素的比率并以百分比表示

## Key Features / 主要特性

- Custom RGB to HLS conversion algorithm (claimed to be one of the first Java implementations on GitHub)
  自定义RGB到HLS转换算法（声称是GitHub上最早的Java实现之一）
- Configurable scaling factor for processing large images efficiently
  可配置的缩放因子，用于高效处理大图像
- Simple command-line interface
  简单的命令行界面
- Self-contained with minimal dependencies (Java AWT only)
  自包含，依赖极少（仅Java AWT）

## Project Structure / 项目结构

```
GLR/
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── example/
│                   └── GLR/
│                       ├── GLR.java              # Main application class
│                       │                         主应用程序类
│                       ├── RGB2HLS.java          # RGB to HLS conversion and green detection
│                       │                         RGB到HLS转换和绿色检测
│                       ├── ImageUtil.java        # Image resizing utilities
│                       │                         图像调整大小实用工具
│                       └── inputImg/             # Sample test images
│                           │                     示例测试图像
├── pom.xml                                       # Maven configuration
│                           Maven配置
└── README.md
```

## Building and Running / 构建和运行

### Prerequisites / 前置条件
- Java JDK 11 or higher
  Java JDK 11 或更高版本
- Maven 3.0+
  Maven 3.0+

### Build / 构建
```bash
mvn clean package
```

### Run / 运行
```bash
java -jar target/demo-0.0.1-SNAPSHOT.jar
```

The application currently runs with a hardcoded test image path and scaling factor in `GLR.java`:
应用程序当前在`GLR.java`中使用硬编码的测试图像路径和缩放因子运行：
```java
GLRsingle("C:\\Users\\Obama\\IdeaProjects\\demo\\src\\main\\java\\com\\example\\GLR\\inputImg\\greenTest1.png",0.2);
```

To analyze different images, modify the path and scaling factor in the `main` method or enhance the application to accept command-line arguments.
要分析不同的图像，请在`main`方法中修改路径和缩放因子，或增强应用程序以接受命令行参数。

## Color Space Explanation / 色彩空间说明

The application uses HLS (Hue, Lightness, Saturation) color space rather than RGB because:
此应用程序使用HLS（色相、亮度、饱和度）色彩空间而不是RGB，因为：
- Hue better represents color perception
  色相更能代表色彩感知
- Separation of color information (Hue) from brightness (Lightness) and purity (Saturation)
  将色彩信息（色相）与亮度（亮度）和纯度（饱和度）分离
- More intuitive for defining color ranges (e.g., "green" is a specific hue range)
  定义色彩范围更直观（例如，“绿色”是特定的色相范围）

## Custom RGB to HLS Implementation / 自定义RGB到HLS实现

The `RGB2HLS.java` file contains a custom implementation of the RGB to HLS conversion algorithm. According to the project comments, this was one of the first Java implementations of this conversion available on GitHub.
`RGB2HLS.java`文件包含RGB到HLS转换算法的自定义实现。根据项目注释，这是GitHub上最早的此转换的Java实现之一。

## Limitations and Future Improvements / 局限性和未来改进

1. **Hardcoded Parameters**: Image path and scaling factor are currently hardcoded
   **硬编码参数**：图像路径和缩放因子当前是硬编码的
2. **No Command-Line Interface**: Would benefit from accepting arguments for flexibility
   **无命令行界面**：接受参数以提高灵活性将是有益的
3. **Limited Error Handling**: Basic error handling for image loading
   **错误处理有限**：图像加载的基本错误处理
4. **Performance**: Could be optimized for very large images
   **性能**：可以针对非常大的图像进行优化

## License / 许可证

This project is available for educational and experimental purposes.
此项目仅供教育和实验目的使用。

---
*Last updated: March 31, 2026*
*最后更新：2026年3月31日*