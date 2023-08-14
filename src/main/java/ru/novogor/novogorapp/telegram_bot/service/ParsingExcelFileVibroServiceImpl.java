package ru.novogor.novogorapp.telegram_bot.service;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import ru.novogor.novogorapp.telegram_bot.entity.Pump;
import ru.novogor.novogorapp.telegram_bot.entity.Station;
import ru.novogor.novogorapp.telegram_bot.entity.Status;

import javax.mail.MessagingException;
import javax.mail.Part;
import javax.mail.internet.MimeBodyPart;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;

@Service
public class ParsingExcelFileVibroServiceImpl implements ParsingExcelFileVibroService {
    private String nameStation;
    private String namePump;
    private String status;
    private String note;
    private Date dateVibroDiagnostic;

    @Override
    public Pump getPumpFromExcelFileVibro(MimeBodyPart part) throws MessagingException, IOException, ParseException {
        Pump pump = null;
        if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) {
            try (InputStream inputStream = part.getInputStream()) {
                XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
                Sheet sheet = workbook.getSheetAt(0);
                //проходим по всему листу
                for (Row row : sheet) {
                    Iterator<Cell> cells = row.iterator();
                    while (cells.hasNext()) {
                        Cell cell = cells.next();
                        CellType cellType = cell.getCellType();
                        if (cellType == CellType.STRING) {
                            String value = cell.getStringCellValue();
                            if (value.equalsIgnoreCase("цех")) {
                                while (cells.hasNext()) {
                                    String cellValue = cells.next().getStringCellValue().toLowerCase(Locale.ROOT);
                                    if (!cellValue.isEmpty()) {
                                        nameStation = cellValue.split(",")[1].trim();
                                        break;
                                    }
                                }
                            }
                            else if (value.equalsIgnoreCase("технологический индекс")) {
                                while (cells.hasNext()) {
                                    String cellValue = cells.next().getStringCellValue();
                                    if (!cellValue.isEmpty()) {
                                        namePump = cellValue.substring(cellValue.indexOf("насос") + 6);
                                        break;
                                    }
                                }
                            }
                            else if (value.equalsIgnoreCase("по замеру от")) {
                                while (cells.hasNext()) {
                                    Date cellValue = cells.next().getDateCellValue();
                                    if (cellValue != null) {
                                        dateVibroDiagnostic = cellValue;
                                        break;
                                    }
                                }
                            }
                            else if (value.equalsIgnoreCase("общее вибросостояние на :")) {
                                while (cells.hasNext()) {
                                    String cellValue = cells.next().getStringCellValue();
                                    if (!cellValue.isEmpty()) {
                                        status = cellValue;
                                        break;
                                    }
                                }
                            }
                            else if (value.equalsIgnoreCase("примечания:")) {
                                while (cells.hasNext()) {
                                    String cellValue = cells.next().getStringCellValue();
                                    if (!cellValue.isEmpty()) {
                                        note = cellValue;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            pump = new Pump();
            Station station = new Station();
            Status status = new Status();
            station.setName(nameStation);
            status.setName(this.status);
            pump.setName(namePump);
            pump.setStation(station);
            pump.setNote(note);
            pump.setStatus(status);
            pump.setDateVibroDiagnostic(dateVibroDiagnostic);
        }

        return pump;
    }
}
