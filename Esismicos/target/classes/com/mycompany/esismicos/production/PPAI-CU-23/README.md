### Prueba de push

Julian
Vale

### Ejecucion en Linux

javac -d ./bin/ ./src/*.java

java -cp ./bin/ Playground

### Y como lo ejecuto en windows?

No se, hay que copilar y ejecutar

### Que carajos es el Playground?

Es la simulacion del sistema, primero crear varios objetos de cada clase y crea
los eventos sismicos que son la clave del sistema. Los eventos sismicos estan en una
coleccion llamada eventosSismicos que se las paso al gestor para que pueda iterar.

Al final de todo esta el metodo Main que crea la pantalla el gestor y la dependencia mutua.

### Como renderizaste la pantalla?

Uso swing, es para hacer ventanas de escritorio. La clase pantalla se extiende (herencia) de una
que ya me provee la libreria

# Recurden la regla de ORO

No arregles lo que ya funciona
