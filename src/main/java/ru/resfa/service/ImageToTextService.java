package ru.resfa.service;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;

public class ImageToTextService {

    public static void main(String[] args) {
        // Создаем экземпляр объекта Tesseract
        Tesseract tesseract = new Tesseract();

        // Устанавливаем путь к файлу, содержащему обученные данные для распознавания текста на русском языке
        tesseract.setDatapath("src/main/resources/tesseract_data/");

        // Устанавливаем язык OCR на русский
        tesseract.setLanguage("rus");

        try {
            // Считываем текст с изображения
            //File imageFile = new File("src/main/resources/image/2023/18810550230621578693_1.jpg");
            File imageFile = new File("src/main/resources/image/2023/27.07.2023.JPG");
            String extractedText = tesseract.doOCR(imageFile);

            // Выводим извлеченный текст
            System.out.println(extractedText);
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }
    }
}

