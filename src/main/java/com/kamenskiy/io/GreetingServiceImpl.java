package com.kamenskiy.io;


import com.kamenskiy.grpc.GreetingServiceGrpc;
import com.kamenskiy.grpc.GreetingServiceOuterClass;
import io.grpc.stub.StreamObserver;

public class GreetingServiceImpl extends GreetingServiceGrpc.GreetingServiceImplBase {
    @Override
    public void greeting(GreetingServiceOuterClass.HelloRequest request,
                         StreamObserver<GreetingServiceOuterClass.HelloResponse> responseObserver) {
        for (int i = 0; i < 10000; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            GreetingServiceOuterClass.HelloResponse response = GreetingServiceOuterClass.
                    HelloResponse.newBuilder().setGreeting("Hello from server " + request.getName()).build();
            responseObserver.onNext(response);
        }

        responseObserver.onCompleted();
    }
}
