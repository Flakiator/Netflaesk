import Domain.MediaImpl;

import javax.swing.*;

class MediaButton extends JButton {
    private MediaImpl media;
    MediaButton(Icon icon, MediaImpl media)
    {
        super(icon);
        this.media = media;
    }
    public MediaImpl getMedia()
    {
        return media;
    }
}