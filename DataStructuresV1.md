#Discussing the Data structures used in the benchamrk

We are serializing a tree of objects with the root being MediaContent.
```
MediaContent
|
|----Media{
|            URI="http://javaone.com/keynote.mpg"
|            Format="video/mpg4"
|            Title="Javaone Keynote"
|            Duration=1234567
|            Bitrate=123
|            Person="Bill Gates"
|            Person="Steve Jobs"
|            Player=Player.JAVA(enum)
|          }
|
|----Image {
|            URI="http://javaone.com/keynote_large.jpg"
|            Size=Size.LARGE(enum)
|            Title="Javaone Keynote"
|          }
|
|----Image {
|            URI="http://javaone.com/keynote_thumbnail.jpg"
|            Size=Size.SMALL(enum)
|            Title="Javaone Keynote"
|          }
```