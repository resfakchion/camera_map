package ru.resfa.service;

import ai.djl.MalformedModelException;
import ai.djl.modality.cv.Image;
import ai.djl.modality.cv.ImageFactory;
import ai.djl.modality.cv.output.DetectedObjects;
import ai.djl.paddlepaddle.zoo.cv.objectdetection.PpWordDetectionTranslator;
import ai.djl.repository.zoo.Criteria;
import ai.djl.repository.zoo.ModelNotFoundException;
import ai.djl.translate.TranslateException;

import java.io.IOException;
import java.nio.file.Path;
import java.util.concurrent.ConcurrentHashMap;

public class NewImageToText {

    public static void main(String[] args) throws IOException, ModelNotFoundException, MalformedModelException, TranslateException {

        // Tutorial - https://docs.djl.ai/docs/demos/jupyter/paddlepaddle/paddle_ocr_java.html
        Path path = Path.of("C:/work/camera_map/src/main/resources/image/2023/1.jpg");
        String url = "https://resources.djl.ai/images/flight_ticket.jpg";
        Path modelPath = Path.of("src/main/resources/det_db.zip");
        Image img = ImageFactory.getInstance().fromFile(path);
        img.getWrappedImage();
        var criteria1 = Criteria.builder()
                .optEngine("PaddlePaddle")
                .setTypes(Image.class, DetectedObjects.class)
                .optModelPath(modelPath)
                .optTranslator(new PpWordDetectionTranslator(new ConcurrentHashMap<String, String>()))
                .build();
        var detectionModel = criteria1.loadModel();
        var detector = detectionModel.newPredictor();
        var detectedObj = detector.predict(img);
        Image newImage = img.duplicate();
        newImage.drawBoundingBoxes(detectedObj);
        newImage.getWrappedImage();
    }

}
