package com.apress.springrecipes.court.web.view;

import com.apress.springrecipes.court.domain.Reservation;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

public class ExcelReservationSummary extends AbstractExcelView {

    protected void buildExcelDocument(Map model, HSSFWorkbook workbook,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        List<Reservation> reservations = (List) model.get("reservations");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        HSSFSheet sheet = workbook.createSheet();

        HSSFRow header = sheet.createRow(0);
        header.createCell((short) 0).setCellValue("Court Name");
        header.createCell((short) 1).setCellValue("Date");
        header.createCell((short) 2).setCellValue("Hour");
        header.createCell((short) 3).setCellValue("Player Name");
        header.createCell((short) 4).setCellValue("Player Phone");

        int rowNum = 1;
        for (Reservation reservation : reservations) {
            HSSFRow row = sheet.createRow(rowNum++);
            row.createCell((short) 0).setCellValue(reservation.getCourtName());
            row.createCell((short) 1).setCellValue(
                    dateFormat.format(reservation.getDate()));
            row.createCell((short) 2).setCellValue(reservation.getHour());
            row.createCell((short) 3).setCellValue(
                    reservation.getPlayer().getName());
            row.createCell((short) 4).setCellValue(
                    reservation.getPlayer().getPhone());
        }
    }
}
