import math

class Vector3D:
    def __init__(self, x=0.0, y=0.0, z=0.0):
        self.x = float(x)
        self.y = float(y)
        self.z = float(z)

    # a) Suma: c = a + b
    def __add__(self, other):
        return Vector3D(self.x + other.x, self.y + other.y, self.z + other.z)

    # b) Multiplicación escalar: r * a y a * r
    def __mul__(self, scalar):
        return Vector3D(self.x * scalar, self.y * scalar, self.z * scalar)

    def __rmul__(self, scalar):
        return self.__mul__(scalar)

    # c) Longitud: |a|
    def longitud(self):
        return math.sqrt(self.x**2 + self.y**2 + self.z**2)

    # d) Normal: a / |a|
    def normal(self):
        mod = self.longitud()
        if mod == 0:
            raise ValueError("No se puede normalizar un vector nulo.")
        return Vector3D(self.x / mod, self.y / mod, self.z / mod)

    # e) Producto escalar (usando el operador @): a @ b
    def __matmul__(self, other):
        return self.x * other.x + self.y * other.y + self.z * other.z

    # f) Producto vectorial (usando el operador ^): a ^ b
    def __xor__(self, other):
        rx = self.y * other.z - self.z * other.y
        ry = self.z * other.x - self.x * other.z
        rz = self.x * other.y - self.y * other.x
        return Vector3D(rx, ry, rz)

    def __str__(self):
        return f"({self.x}, {self.y}, {self.z})"

# Programa de prueba
if __name__ == "__main__":
    a = Vector3D(1.0, 2.0, 3.0)
    b = Vector3D(4.0, 5.0, 6.0)

    print("a =", a)
    print("b =", b)
    print("Suma (a + b) =", a + b)
    print("Multiplicación escalar (3 * a) =", 3 * a)
    print("Longitud de a (|a|) =", a.longitud())
    print("Normal de a =", a.normal())
    print("Producto escalar (a @ b) =", a @ b)
    print("Producto vectorial (a ^ b) =", a ^ b)