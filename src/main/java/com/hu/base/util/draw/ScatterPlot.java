package com.hu.base.util.draw;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.charts.ChartAxis;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xddf.usermodel.*;
import org.apache.poi.xddf.usermodel.chart.*;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.xssf.usermodel.charts.XSSFManualLayout;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class ScatterPlot {

    public static void main(String[] args) throws IOException {
        try (XSSFWorkbook wb = new XSSFWorkbook()) {
            XSSFSheet sheet = wb.createSheet("FEOL_PIN检查机Defect叠图");
            final int NUM_OF_ROWS = 200;
            final int NUM_OF_COLUMNS = 2;

            // Create a row and put some cells in it. Rows are 0 based.
            Row row;
            Cell cell;
            for (int rowIndex = 0; rowIndex < NUM_OF_ROWS; rowIndex++) {
                row = sheet.createRow((short) rowIndex);
                for (int colIndex = 0; colIndex < NUM_OF_COLUMNS; colIndex++) {
                    cell = row.createCell((short) colIndex);
                    cell.setCellValue(getRandom());
                }
            }

            XSSFDrawing drawing = sheet.createDrawingPatriarch();
            XSSFClientAnchor anchor = drawing.createAnchor(0, 0, 0, 0, 4, 3, 13, 18);

            XSSFChart chart = drawing.createChart(anchor);
//            XDDFChartLegend legend = chart.getOrAddLegend();
//            legend.setPosition(LegendPosition.TOP_RIGHT);
            chart.setTitleText("CCPIN270 M6590JB1 Map");
            chart.getTitle().setOverlay(false);
            XDDFValueAxis bottomAxis = chart.createValueAxis(AxisPosition.BOTTOM);
            bottomAxis.setMaximum(750);
            bottomAxis.setMinimum(-750);

//            bottomAxis.setTitle("x"); // https://stackoverflow.com/questions/32010765
            XDDFValueAxis leftAxis = chart.createValueAxis(AxisPosition.LEFT);
            leftAxis.setMaximum(875);
            leftAxis.setMinimum(-925);
//            leftAxis.setTitle("f(x)");
//            leftAxis.setCrosses(AxisCrosses.AUTO_ZERO);

            XDDFDataSource<Double> xs = XDDFDataSourcesFactory.fromNumericCellRange(sheet, new CellRangeAddress(0, 200, 0, 0));
            XDDFNumericalDataSource<Double> ys = XDDFDataSourcesFactory.fromNumericCellRange(sheet, new CellRangeAddress(0, 200, 1, 1));


            XDDFScatterChartData data = (XDDFScatterChartData) chart.createData(ChartTypes.SCATTER, bottomAxis, leftAxis);
            XDDFScatterChartData.Series series = (XDDFScatterChartData.Series) data.addSeries(xs, ys);
//            series1.setTitle("2x", null); // https://stackoverflow.com/questions/21855842
            series.setSmooth(true); // https://stackoverflow.com/questions/39636138
//
            series.setMarkerStyle(MarkerStyle.CIRCLE);
            series.setMarkerSize((short) 4);
            XDDFSolidFillProperties fillMarker = new XDDFSolidFillProperties(XDDFColor.from(new byte[]{(byte)0x00, (byte)0x00, (byte)0xFF}));
//            XDDFShapeProperties propertiesMarker = new XDDFShapeProperties();
//            propertiesMarker.setFillProperties(fillMarker);

            setLineNoFill(series);

            XDDFNoFillProperties noFill = new XDDFNoFillProperties();
            XDDFShapeProperties propertiesMarker = new XDDFShapeProperties();
            propertiesMarker.setFillProperties(fillMarker);
            XDDFLineProperties lineProperties = new XDDFLineProperties();
            lineProperties.setFillProperties(noFill);
            propertiesMarker.setLineProperties(lineProperties);
            chart.getCTChart().getPlotArea().getScatterChartArray(0).getSerArray(0).getMarker()
                    .addNewSpPr().set(propertiesMarker.getXmlObject());
            chart.plot(data);



            // Write the output to a file
            try (FileOutputStream fileOut = new FileOutputStream("C:\\Users\\hutiantian\\Desktop\\ooxml-scatter-chart1.xlsx")) {
                wb.write(fileOut);
            }
        }
    }

    private static void setLineNoFill(XDDFScatterChartData.Series series) {
        XDDFNoFillProperties noFillProperties = new XDDFNoFillProperties();
        XDDFLineProperties lineProperties = new XDDFLineProperties();
        lineProperties.setFillProperties(noFillProperties);
        XDDFShapeProperties shapeProperties = series.getShapeProperties();
        if (shapeProperties == null) shapeProperties = new XDDFShapeProperties();
        shapeProperties.setLineProperties(lineProperties);
        series.setShapeProperties(shapeProperties);
    }

    private static double getRandom() {
        Random random = new Random();
        return (random.nextInt(20000) - 10000) * 0.1;
    }

}
