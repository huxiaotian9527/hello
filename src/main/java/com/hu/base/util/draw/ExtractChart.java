package com.hu.base.util.draw;

import com.aspose.cells.*;

import java.io.*;

public class ExtractChart {
    public static void main(String[] args) throws Exception{
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        InputStream in = new ByteArrayInputStream(bao.toByteArray());
        Workbook workbook = new Workbook("C:\\Users\\hutiantian\\Desktop\\ooxml-scatter-chart1.xlsx");
        Worksheet sheet = workbook.getWorksheets().get(0);
        Chart chart = sheet.getCharts().get(0);
        chart.setShowLegend(false);
        ImageOrPrintOptions imgOpts = new ImageOrPrintOptions();
        imgOpts.setImageFormat(ImageFormat.getPng());
        //Save the chart image file.
        OutputStream outputStream = new FileOutputStream("C:\\Users\\hutiantian\\Desktop\\MyChartImage.png");
        chart.toImage(outputStream, imgOpts);
    }

}
