package itvo.acuacultura.Model;

import android.graphics.drawable.Drawable;

/**
 * Created by HP on 10/04/2017.
 */

public class Picture {
    private Drawable image;
    private String enfermedad;

    public Picture(Drawable image, String enfermedad) {
        this.image = image;
        this.enfermedad = enfermedad;
    }

    public Drawable getImage() {
        return image;
    }
    public String getEnfermedad() {
        return enfermedad;
    }

}
