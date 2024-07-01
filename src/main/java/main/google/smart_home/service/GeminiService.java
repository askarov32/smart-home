package main.google.smart_home.service;

import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.text.Text;
import com.google.mlkit.vision.text.TextRecognizer;
import com.google.android.gms.tasks.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class GeminiService {

    private final TextRecognizer textRecognizer;

    @Autowired
    public GeminiService(TextRecognizer textRecognizer) {
        this.textRecognizer = textRecognizer;
    }

    public void performAction(InputImage image) {
        Task<Text> result =
                textRecognizer.process(image)
                        .addOnSuccessListener(firebaseVisionText -> {
                            System.out.println(firebaseVisionText.getText());
                        })
                        .addOnFailureListener(
                                e -> {
                                    e.printStackTrace();
                                });
    }
}
