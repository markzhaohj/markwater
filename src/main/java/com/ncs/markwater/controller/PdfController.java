package com.ncs.markwater.controller;

import com.aspose.pdf.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RestController
@RequestMapping("/api/ncs")
public class PdfController {

    @PostMapping("/addWatermarkText")
    public ResponseEntity<byte[]> addTextWatermark(@RequestParam("file") MultipartFile file) throws IOException{

        // Load PDF file
        Document pdfDocument = new Document(file.getInputStream());
        TextStamp waterText = new TextStamp("Watermarkzhaohongjun");
        waterText.setWordWrap(true);

        waterText.setBackground(true);
        waterText.setXIndent(300);//设置位置
        waterText.setYIndent(300);
        waterText.setRotateAngle(50);//设置旋转角度
        waterText.getTextState().setFont(FontRepository.findFont("Arial"));
        waterText.getTextState().setFontSize(34.0F);//设置字体大小
        waterText.getTextState().setFontStyle(FontStyles.Italic);
        waterText.getTextState().setForegroundColor(Color.getLightGray());//设置字体颜色

        waterText.setVerticalAlignment(VerticalAlignment.Center);
        waterText.setHorizontalAlignment(HorizontalAlignment.Center);

        for (Page page: pdfDocument.getPages()) {
            page.addStamp(waterText);
        }


        // Save PDF file
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        pdfDocument.save(outputStream);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"watermarked.pdf\"")
                .contentType(MediaType.APPLICATION_PDF)
                .body(outputStream.toByteArray());
    }

    @PostMapping("/addWatermarkImage")
    public ResponseEntity<byte[]> addImageWatermark(@RequestParam("file") MultipartFile file) throws IOException {
         // Load PDF file
         Document pdfDocument = new Document(file.getInputStream());

         String currentPath = System.getProperty("user.dir");

         ImageStamp waterImage = new ImageStamp("watermark.png");
         waterImage.setWidth(280);
         waterImage.setHeight(280);
         waterImage.setOpacity(0.7);
         waterImage.setRotateAngle(50);
         waterImage.setVerticalAlignment(VerticalAlignment.Center);
         waterImage.setHorizontalAlignment(HorizontalAlignment.Center);

         for (Page page: pdfDocument.getPages()) {
            page.addStamp(waterImage);
         }

         // Save PDF File
         ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
         pdfDocument.save(outputStream);
         return ResponseEntity.ok()
                 .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"watermarked.pdf\"")
                 .contentType(MediaType.APPLICATION_PDF)
                 .body(outputStream.toByteArray());
    }

}
