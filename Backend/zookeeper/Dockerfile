# Use base image
FROM eclipse-temurin:11-jre

# Set environment
ENV ZOOKEEPER_VERSION=3.8.1
ENV ZOOKEEPER_HOME=/opt/zookeeper
ENV PATH=$ZOOKEEPER_HOME/bin:$PATH

# Install dependencies
RUN apt-get update && apt-get install -y wget bash && \
    wget https://archive.apache.org/dist/zookeeper/zookeeper-${ZOOKEEPER_VERSION}/apache-zookeeper-${ZOOKEEPER_VERSION}-bin.tar.gz && \
    tar -xzf apache-zookeeper-${ZOOKEEPER_VERSION}-bin.tar.gz && \
    mv apache-zookeeper-${ZOOKEEPER_VERSION}-bin $ZOOKEEPER_HOME && \
    rm apache-zookeeper-${ZOOKEEPER_VERSION}-bin.tar.gz

# Copy config and set workdir
WORKDIR $ZOOKEEPER_HOME
COPY zoo.cfg $ZOOKEEPER_HOME/conf/zoo.cfg

EXPOSE 2181

CMD ["bash", "-c", "$ZOOKEEPER_HOME/bin/zkServer.sh start-foreground"]
