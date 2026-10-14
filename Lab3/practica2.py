# JUEGO 2 - Todo en un solo archivo
import random


# CLASE PADRE
class Juego:
    def _init_(self, numeroDeVidas):
        self.vidasIniciales = numeroDeVidas
        self.numeroDeVidas = numeroDeVidas
        self.record = 0

    def reiniciaPartida(self):
        # Devuelve las vidas al valor inicial
        self.numeroDeVidas = self.vidasIniciales

    def actualizaRecord(self):
        # El record es la mayor cantidad de vidas que han sobrado
        if self.numeroDeVidas > self.record:
            self.record = self.numeroDeVidas
        print("Record actual:", self.record)

    def quitaVida(self):
        # Quita una vida. Devuelve True si aún quedan vidas, False si no
        self.numeroDeVidas = self.numeroDeVidas - 1
        if self.numeroDeVidas > 0:
            print("Te quedan", self.numeroDeVidas, "vidas")
            return True
        else:
            print("No te quedan más vidas")
            return False


# CLASE HIJA DE Juego
class JuegoAdivinaNumero(Juego):
    def _init_(self, numeroDeVidas):
        Juego._init_(self, numeroDeVidas)
        self.numeroAAdivinar = 0

    def validaNumero(self, numero):
        # True si el número está entre 0 y 10
        if numero >= 0 and numero <= 10:
            return True
        else:
            return False

    def juega(self):
        self.reiniciaPartida()
        self.numeroAAdivinar = random.randint(0, 10)
        print("Adivina un número entre 0 y 10")
        seguir = True
        while seguir:
            numero = int(input("Ingresa un número: "))
            if not self.validaNumero(numero):
                # Número no válido: no pierde vida, vuelve a intentar
                print("Número no válido, intenta de nuevo")
            elif numero == self.numeroAAdivinar:
                print("Acertaste!!")
                self.actualizaRecord()
                seguir = False
            else:
                if self.quitaVida():
                    if self.numeroAAdivinar > numero:
                        print("El número a adivinar es mayor")
                    else:
                        print("El número a adivinar es menor")
                else:
                    print("El número era:", self.numeroAAdivinar)
                    seguir = False


# CLASE HIJA DE JuegoAdivinaNumero
class JuegoAdivinaPar(JuegoAdivinaNumero):
    def validaNumero(self, numero):
        if numero % 2 == 0 and numero >= 0 and numero <= 10:
            return True
        if numero % 2 != 0:
            print("Error: el número debe ser par")
        return False


# CLASE HIJA DE JuegoAdivinaNumero
class JuegoAdivinaImpar(JuegoAdivinaNumero):
    def validaNumero(self, numero):
        if numero % 2 != 0 and numero >= 0 and numero <= 10:
            return True
        if numero % 2 == 0:
            print("Error: el número debe ser impar")
        return False


# APLICACION (programa principal)
class Aplicacion:
    @staticmethod
    def main():
        juego1 = JuegoAdivinaNumero(3)
        juego2 = JuegoAdivinaPar(3)
        juego3 = JuegoAdivinaImpar(3)

        print("--- Juego normal ---")
        juego1.juega()
        print("--- Juego de pares ---")
        juego2.juega()
        print("--- Juego de impares ---")
        juego3.juega()


Aplicacion.main()