As the compression ratio increases from 0% to around 65%, the image size gets changed 
but not necessarily decreased and the visual difference is not significant 
compared with the original image. 

As the compression ratio increases from 65% to around 90%, the image size gets decreased 
to some extent but the visual difference is not significant 
compared with the original image. 

Only when the ratio begins from 90% to 99%, the size of image reduces significantly 
and the visual difference becomes noticeable.

So we think it’s better to compress images with ratio around 90% 
for better visual result and relative small file size.

###################
How to Use
###################
1. Download the *.java files in src folder
2. javac all of these *.java files
3. in *.class directory folder: 
run "java ImageCompressor -o output-image-dir-name.png -compress 90 -progressive -i input-image-dir-name.png"
4. 90 can be changed to any ratio you want to compress