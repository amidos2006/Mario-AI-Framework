from sklearn.linear_model import LinearRegression
import numpy as np


def handle(s):
    coords = s.split('-')
    # skip first two, they are usually weird
    coords = coords[2:]

    xs = []
    ys = []
    for coord in coords:
        x, y = map(float, coord.split(':'))
        xs.append(x)
        # height normalized by tile_size
        ys.append(y / 16)

    model = LinearRegression()
    model.fit(scikit_reshape_1d(xs), scikit_reshape_1d(ys))

    y_pred = model.predict(scikit_reshape_1d(xs))
    y_pred = list(y_pred.reshape(-1))
    distances = list(map(lambda a: abs(a[0] - a[1]), zip(ys, y_pred)))

    avg_distance = np.mean(distances)

    # the most non-linear level swaps between top row and bottom row constantly
    # thus, maximum (however, unplayable) distance is half of the level height

    max_avg_distance = 7.0

    return 1 - avg_distance / max_avg_distance


def scikit_reshape_1d(l):
    return np.array(l).reshape(-1, 1)