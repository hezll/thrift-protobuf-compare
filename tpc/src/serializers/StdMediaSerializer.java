package serializers;

import java.util.*;

import serializers.java.Image;
import serializers.java.Media;
import serializers.java.MediaContent;

/**
 * Shared base class for those serializers that operate on POJOs.
 */
public abstract class StdMediaSerializer
    implements ObjectSerializer<MediaContent>
{
    public final static int FIELD_IX_MEDIA = 1;
    public final static String FIELD_NAME_MEDIA = "md";
    public final static int FIELD_IX_IMAGES = 2;
    public final static String FIELD_NAME_IMAGES = "im";
    public final static int FIELD_IX_PLAYER = 3;
    public final static String FIELD_NAME_PLAYER = "pl";
    public final static int FIELD_IX_URI = 4;
    public final static String FIELD_NAME_URI = "ul";
    public final static int FIELD_IX_TITLE = 5;
    public final static String FIELD_NAME_TITLE = "tl";
    public final static int FIELD_IX_WIDTH = 6;
    public final static String FIELD_NAME_WIDTH = "wd";
    public final static int FIELD_IX_HEIGHT = 7;
    public final static String FIELD_NAME_HEIGHT = "hg";
    public final static int FIELD_IX_FORMAT = 8;
    public final static String FIELD_NAME_FORMAT = "fr";
    public final static int FIELD_IX_DURATION = 9;
    public final static String FIELD_NAME_DURATION = "dr";
    public final static int FIELD_IX_SIZE = 10;
    public final static String FIELD_NAME_SIZE = "sz";
    public final static int FIELD_IX_BITRATE = 11;
    public final static String FIELD_NAME_BITRATE = "br";
    public final static int FIELD_IX_PERSONS = 12;
    public final static String FIELD_NAME_PERSONS = "pr";

    static HashMap<String,Integer> fieldToIndex = new HashMap<String,Integer>();
    static {
        fieldToIndex.put(FIELD_NAME_MEDIA, FIELD_IX_MEDIA);
        fieldToIndex.put(FIELD_NAME_IMAGES, FIELD_IX_IMAGES);
        fieldToIndex.put(FIELD_NAME_PLAYER, FIELD_IX_PLAYER);
        fieldToIndex.put(FIELD_NAME_URI, FIELD_IX_URI);
        fieldToIndex.put(FIELD_NAME_TITLE, FIELD_IX_TITLE);
        fieldToIndex.put(FIELD_NAME_WIDTH, FIELD_IX_WIDTH);
        fieldToIndex.put(FIELD_NAME_HEIGHT, FIELD_IX_HEIGHT);
        fieldToIndex.put(FIELD_NAME_FORMAT, FIELD_IX_FORMAT);
        fieldToIndex.put(FIELD_NAME_DURATION, FIELD_IX_DURATION);
        fieldToIndex.put(FIELD_NAME_SIZE, FIELD_IX_SIZE);
        fieldToIndex.put(FIELD_NAME_BITRATE, FIELD_IX_BITRATE);
        fieldToIndex.put(FIELD_NAME_PERSONS, FIELD_IX_PERSONS);
    }

    final String name;

    protected StdMediaSerializer(String n)
    {
        name = n;
    }

    public final String getName()
    {
        return name;
    }

    public final MediaContent create() throws Exception
    {
        Media media = new Media(null, "video/mpg4", Media.Player.JAVA, "Javaone Keynote", "http://javaone.com/keynote.mpg", 1234567, 123, 0, 0, 0);
        media.addToPerson("Bill Gates");
        media.addToPerson("Steve Jobs");
        MediaContent content = new MediaContent(media);
        content.addImage(new Image(0, "Javaone Keynote", "http://javaone.com/keynote_large.jpg", 0, Image.Size.LARGE));
        content.addImage(new Image(0, "Javaone Keynote", "http://javaone.com/keynote_thumbnail.jpg", 0, Image.Size.SMALL));
        return content;
    }
}
