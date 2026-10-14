# SISTEMA DE VEHICULO AUTONOMO - Todo en un solo archivo


# CLASE PADRE
class Sensor:
    # Atributo de clase (compartido por todos los sensores)
    tipo = "Genérico"

    def _init_(self, id):
        # Atributos de instancia (propios de cada objeto)
        self.id = id
        self.encendido = False

    def activar(self):
        self.encendido = True
        print(f"Sensor {self.id} activado")

    def desactivar(self):
        self.encendido = False
        print(f"Sensor {self.id} desactivado")


# CLASE HIJA DE Sensor
class Camara(Sensor):
    # Atributo de clase (sobrescribe el de Sensor)
    tipo = "Cámara"

    def _init_(self, id, resolucion):
        Sensor._init_(self, id)
        # Atributo de instancia
        self.resolucion = resolucion


# CLASE HIJA DE Sensor
class SensorLuzLaser(Sensor):
    # Atributo de clase (sobrescribe el de Sensor)
    tipo = "Luz Laser"

    def _init_(self, id, alcance):
        Sensor._init_(self, id)
        # Atributo de instancia
        self.alcance = alcance


# HERENCIA MULTIPLE: hereda de Camara y SensorLuzLaser
class VehiculoAutonomo(Camara, SensorLuzLaser):
    # Atributo de clase (sobrescribe el de las clases padre)
    tipo = "Vehículo Autónomo"

    def _init_(self, id, resolucion, alcance):
        Camara._init_(self, id, resolucion)
        SensorLuzLaser._init_(self, id, alcance)


# PROGRAMA PRINCIPAL
auto = VehiculoAutonomo("VA-01", "1920x1080", 150)

print("Tipo:", auto.tipo)
print("ID:", auto.id)
print("Resolución:", auto.resolucion)
print("Alcance:", auto.alcance)
print("Encendido:", auto.encendido)

auto.activar()
print("Encendido:", auto.encendido)

auto.desactivar()
print("Encendido:", auto.encendido)