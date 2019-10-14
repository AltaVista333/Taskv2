Test Task CFT
=============================
Компиляция
------------
Требования: java 1.8+, maven

        $ mvn package

Запуск
------------

        $ java -jar target/TestTaskCFT.jar <input file> <output file>

Запишет координаты первого равнобедренного треугольника из **input file** с максимальной площадью в **output file**.
 
        $ java -jar target/TestTaskCFT.jar <input file> <output file> all

Запишет координаты всех равнобедренных треугольников из **input file** с максимальной площадью в **output file**.
