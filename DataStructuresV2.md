The data value used in the benchmark:
```
MediaContent {

   media = Media {
      uri = "http://javaone.com/keynote.mpg"
      title = "Javaone Keynote"
      width = 640
      height = 480
      format = "video/mpg4"
      duration = 18000000    // half hour in milliseconds
      size = 58982400        // bitrate * duration in seconds / 8 bits per byte
      bitrate = 262144       // 256k
      persons = ["Bill Gates", "Steve Jobs"]
      player = JAVA
      copyright = null
   }

   images = [
      Image {
         uri = "http://javaone.com/keynote_large.jpg"
         title = "Javaone Keynote"
         width = 1024
         height = 768
         size = LARGE
      }
      Image {
         uri = "http://javaone.com/keynote_small.jpg"
         title = "Javaone Keynote"
         width = 320
         height = 240
         size = SMALL
      }
   ]

}
```

The data types ("?" indicates an optional value):
```
record Image = {
   uri: String
   title: String?
   width: Int32
   height: Int32
   size: Size

   enum Size = { SMALL, LARGE, }
}

record Media = {
   uri: String
   title: String?
   width: Int32
   height: Int32
   format: String
   duration: Int64
   size: Int64
   bitrate: Int32?
   persons: List<String>
   player: Player
   copyright: String?

   enum Player = { JAVA, FLASH, }
}

record MediaContent = {
   images: List<Image>
   media: Media
}
```