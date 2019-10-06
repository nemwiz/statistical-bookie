FROM alpine:3.7

COPY ./app /
COPY requirements.txt /

RUN apk add --no-cache python3 && \
    python3 -m ensurepip && \
    rm -r /usr/lib/python*/ensurepip && \
    pip3 install --upgrade pip setuptools && \
    pip3 install -r requirements.txt

CMD gunicorn --bind 0.0.0.0:$PORT --workers 3 wsgi:app