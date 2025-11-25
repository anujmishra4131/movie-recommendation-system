# Use base image
FROM eclipse-temurin:11-jre

# Set environment
ENV KAFKA_VERSION=3.6.1
ENV SCALA_VERSION=2.13
ENV KAFKA_HOME=/opt/kafka
ENV PATH=$KAFKA_HOME/bin:$PATH

# Install dependencies and Kafka
RUN apt-get update && apt-get install -y wget bash && \
    wget https://archive.apache.org/dist/kafka/${KAFKA_VERSION}/kafka_${SCALA_VERSION}-${KAFKA_VERSION}.tgz && \
    tar -xzf kafka_${SCALA_VERSION}-${KAFKA_VERSION}.tgz && \
    mv kafka_${SCALA_VERSION}-${KAFKA_VERSION} $KAFKA_HOME && \
    rm kafka_${SCALA_VERSION}-${KAFKA_VERSION}.tgz

WORKDIR $KAFKA_HOME

# Expose Kafka port
EXPOSE 9092

# Start Kafka broker and connect to Zookeeper
CMD ["bash", "-c", "bin/kafka-server-start.sh config/server.properties --override zookeeper.connect=zookeeper:2181 --override listeners=PLAINTEXT://:9092 --override advertised.listeners=PLAINTEXT://localhost:9092"]
