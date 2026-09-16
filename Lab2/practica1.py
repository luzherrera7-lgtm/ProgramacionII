import math

class Fraccion:
    def __init__(self, a=0, b=1):
        if b == 0:
            raise ValueError("El denominador no puede ser 0.")
        self.__numerador = a
        self.__denominador = b

    def __add__(self, o):
        a = self.__numerador * o.__denominador + self.__denominador * o.__numerador
        b = self.__denominador * o.__denominador
        return Fraccion(a, b)

    def __sub__(self, o):
        a = self.__numerador * o.__denominador - self.__denominador * o.__numerador
        b = self.__denominador * o.__denominador
        return Fraccion(a, b)

    # 1. Multiplicación
    def __mul__(self, o):
        a = self.__numerador * o.__numerador
        b = self.__denominador * o.__denominador
        return Fraccion(a, b)

    # 2. División
    def __truediv__(self, o):
        if o.__numerador == 0:
            raise ZeroDivisionError("No se puede dividir entre una fracción con numerador 0.")
        a = self.__numerador * o.__denominador
        b = self.__denominador * o.__numerador
        return Fraccion(a, b)

    # 3. Igualdad
    def __eq__(self, o):
        if isinstance(o, Fraccion):
            f1 = self.simplifica()
            f2 = o.simplifica()
            return f1.__numerador == f2.__numerador and f1.__denominador == f2.__denominador
        return False

    # 4. Convertir a decimal
    def convertirADecimal(self):
        return self.__numerador / self.__denominador

    # 5. Comprobar si es inversa
    def esInverso(self, o):
        prod = self * o
        return prod.simplifica() == Fraccion(1, 1)

    # 6. Parse de cadena a Fracción
    @staticmethod
    def parseFraccion(str_frac):
        partes = str_frac.split('/')
        num = int(partes[0])
        den = int(partes[1]) if len(partes) > 1 else 1
        return Fraccion(num, den)

    # 7. Simplificar
    def simplifica(self):
        mcd = math.gcd(self.__numerador, self.__denominador)
        num = self.__numerador // mcd
        den = self.__denominador // mcd
        if den < 0:
            num = -num
            den = -den
        return Fraccion(num, den)

    def __str__(self):
        return f"{self.__numerador}/{self.__denominador}"

# Programa de prueba
if __name__ == "__main__":
    f1 = Fraccion(1, 4)
    f2 = Fraccion(4, 3)

    print("f1 =", f1)
    print("f2 =", f2)
    print("Multiplicación (f1 * f2):", f1 * f2)
    print("División (f1 / f2):", f1 / f2)
    print("Decimal f1:", f1.convertirADecimal())
    
    f3 = Fraccion(4, 1)
    print("¿f1 es inverso de f3?:", f1.esInverso(f3))
    
    f_parsed = Fraccion.parseFraccion("-2/3")
    print("Parsed '-2/3':", f_parsed)
    
    f_unsimplified = Fraccion(14, 28)
    print("14/28 simplificado:", f_unsimplified.simplifica())