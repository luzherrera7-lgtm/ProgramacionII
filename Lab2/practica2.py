import math

class MiPunto:
    def __init__(self, x=0.0, y=0.0):
        self.__x = float(x)
        self.__y = float(y)

    def get_x(self):
        return self.__x

    def get_y(self):
        return self.__y

    # Distancia hasta un objeto del tipo MiPunto
    def distancia(self, p):
        return math.sqrt((self.__x - p.get_x())**2 + (self.__y - p.get_y())**2)

    # Distancia hasta coordenadas específicas
    def distancia_coords(self, x, y):
        return math.sqrt((self.__x - x)**2 + (self.__y - y)**2)

# Programa de prueba
if __name__ == "__main__":
    p1 = MiPunto()
    p2 = MiPunto(10, 30.5)

    print(f"Punto 1: ({p1.get_x()}, {p1.get_y()})")
    print(f"Punto 2: ({p2.get_x()}, {p2.get_y()})")
    print(f"Distancia entre p1 y p2: {p1.distancia(p2):.4f}")