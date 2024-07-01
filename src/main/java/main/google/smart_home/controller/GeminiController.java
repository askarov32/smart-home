package main.google.smart_home.controller;

import main.google.smart_home.service.GeminiService;
import com.google.mlkit.vision.common.InputImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/gemini")
public class GeminiController {

    private final GeminiService geminiService;

    @Autowired
    public GeminiController(GeminiService geminiService) {
        this.geminiService = geminiService;
    }

    @GetMapping("/perform-action")
    public String performAction(@RequestParam String imagePath) {
        try {
            Path path = Paths.get(imagePath);
            InputImage image = InputImage.fromFilePath(path.toUri());
            geminiService.performAction(image);
            return "Action performed successfully!";
        } catch (IOException e) {
            e.printStackTrace();
            return "Failed to perform action.";
        }
    }
}