import pandas as pd
from collections import defaultdict
import linearity


def run():
    data = pd.read_csv("data/metrics.csv")
    data = preprocess_data(data)
    print(data)
    # TODO plot!


def preprocess_data(data):
    metric_names = list(data.columns[1:])

    # dictionary that contains methods which parse metrics
    handlers = defaultdict(lambda: lambda s: float(s))
    handlers['linearity'] = linearity.handle

    new_data = pd.DataFrame(columns=data.columns, index=data.index)
    for i, row in data.iterrows():
        new_data.loc[i, 'run'] = row['run']
        for metric_name in metric_names:
            new_data.loc[i, metric_name] = handlers[metric_name](row[metric_name])

    return new_data


if __name__ == '__main__':
    run()
