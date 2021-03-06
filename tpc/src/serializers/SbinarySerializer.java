package serializers;

import java.io.*;

import serializers.scala.Image;
import serializers.scala.Media;
import serializers.scala.MediaContent;
import serializers.scala.Player;
import serializers.scala.SbinarySerializerSupport;
import serializers.scala.Size;


public class SbinarySerializer implements ObjectSerializer<MediaContent>
{
  public MediaContent deserialize (byte[] array) throws Exception
  {
    return SbinarySerializerSupport.deserialize(array);
  }
    public byte[] serialize(MediaContent content) throws IOException, Exception
  {
    return SbinarySerializerSupport.serialize(content);
  }


  public MediaContent create()
  {
    Media media = new Media("http://javaone.com/keynote.mpg", "Javaone Keynote", 0, 0, "video/mpg4", 1234567, 123, 0, Player.JAVA());
    media.addPerson("Bill Gates");
    media.addPerson("Steve Jobs");

    Image image1 = new Image("http://javaone.com/keynote_large.jpg", "Javaone Keynote", 0, 0, Size.LARGE());

    Image image2 = new Image("http://javaone.com/keynote_thumbnail.jpg", "Javaone Keynote", 0, 0, Size.SMALL());

    MediaContent content = new MediaContent(media);
    content.addImage(image1);
    content.addImage(image2);

    return content;
  }

  public String getName ()
  {
    return "sbinary";
  }
}
