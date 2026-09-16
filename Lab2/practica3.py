import math

class AlgebraVectorial:
    @staticmethod
    def _magnitud(v):
        return math.sqrt(sum(x**2 for x in v))

    @staticmethod
    def _dot(a, b):
        return sum(x * y for x, y in zip(a, b))

    @staticmethod
    def _sub(a, b):
        return [x - y for x, y in zip(a, b)]

    @staticmethod
    def _add(a, b):
        return [x + y for x, y in zip(a, b)]

    @staticmethod
    def _cross_3d(a, b):
        return [
            a[1]*b[2] - a[2]*b[1],
            a[2]*b[0] - a[0]*b[2],
            a[0]*b[1] - a[1]*b[0]
        ]

    # a) Perpendicular: |a + b| == |a - b|
    @staticmethod
    def perpendicular(a, b, criterio="diagonales"):
        if criterio == "diagonales":
            return math.isclose(AlgebraVectorial._magnitud(AlgebraVectorial._add(a, b)), 
                                AlgebraVectorial._magnitud(AlgebraVectorial._sub(a, b)))
        # b) Perpendicular: |a - b| == |b - a|
        elif criterio == "simetria":
            return math.isclose(AlgebraVectorial._magnitud(AlgebraVectorial._sub(a, b)), 
                                AlgebraVectorial._magnitud(AlgebraVectorial._sub(b, a)))
        # c) Perpendicular: a . b == 0
        elif criterio == "producto_punto":
            return math.isclose(AlgebraVectorial._dot(a, b), 0.0)
        # d) Perpendicular: |a + b|^2 == |a|^2 + |b|^2
        elif criterio == "pitagoras":
            lhs = AlgebraVectorial._magnitud(AlgebraVectorial._add(a, b))**2
            rhs = AlgebraVectorial._magnitud(a)**2 + AlgebraVectorial._magnitud(b)**2
            return math.isclose(lhs, rhs)
        return False

    # e) Paralela: a = r*b
    @staticmethod
    def paralela(a, b, criterio="escalar"):
        if criterio == "escalar":
            ratios = []
            for x, y in zip(a, b):
                if y == 0:
                    if x != 0:
                        return False
                else:
                    ratios.append(x / y)
            if not ratios:
                return True
            return all(math.isclose(r, ratios[0]) for r in ratios)
        # f) Paralela: a x b == 0
        elif criterio == "producto_cruz":
            cruz = AlgebraVectorial._cross_3d(a, b)
            return all(math.isclose(x, 0.0) for x in cruz)
        return False

    # g) Proyección de a sobre b: (a.b / |b|^2) * b
    @staticmethod
    def proyeccion_de_a_sobre_b(a, b):
        mag_b_sq = AlgebraVectorial._magnitud(b)**2
        if math.isclose(mag_b_sq, 0.0):
            raise ValueError("El vector b no puede ser el vector nulo.")
        factor = AlgebraVectorial._dot(a, b) / mag_b_sq
        return [factor * x for x in b]

    # h) Componente de a en b: (a.b) / |b|
    @staticmethod
    def componente_de_a_en_b(a, b):
        mag_b = AlgebraVectorial._magnitud(b)
        if math.isclose(mag_b, 0.0):
            raise ValueError("El vector b no puede ser el vector nulo.")
        return AlgebraVectorial._dot(a, b) / mag_b

# Prueba
if __name__ == "__main__":
    v1 = [1, 0, 0]
    v2 = [0, 1, 0]

    print("v1 y v2 ortogonales (producto punto):", AlgebraVectorial.perpendicular(v1, v2, "producto_punto"))
    print("Proyección v1 en v2:", AlgebraVectorial.proyeccion_de_a_sobre_b(v1, v2))