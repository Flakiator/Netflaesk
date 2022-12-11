import Domain.MediaImpl;

import javax.swing.*;

class MediaButton extends JButton {
    MediaImpl media;
    MediaButton(Icon icon, MediaImpl media)
    {
        super(icon);
        this.media = media;
    }
    MediaImpl getmedia()
    {
        return media;
    }
}