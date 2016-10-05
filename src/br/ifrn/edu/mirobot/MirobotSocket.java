package br.ifrn.edu.mirobot;
 
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.StatusCode;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;

public class MirobotSocket
{
    private final CountDownLatch closeLatch;
    @SuppressWarnings("unused")
    private Session session;

    public MirobotSocket()
    {
        this.closeLatch = new CountDownLatch(1);
    }

    public boolean awaitClose(int duration, TimeUnit unit) throws InterruptedException
    {
        return this.closeLatch.await(duration,unit);
    }

    @OnWebSocketClose
    public void onClose(int statusCode, String reason)
    {
        System.out.printf("Connection closed: %d - %s%n",statusCode,reason);
        this.session = null;
        this.closeLatch.countDown();
    }

    @OnWebSocketConnect
    public void onConnect(Session session)
    {
        System.out.printf("Got connect: %s%n",session);
        this.session = session;
        try
        {
            Future<Void> fut;
            fut = session.getRemote().sendStringByFuture("Hello");
            fut.get(2,TimeUnit.SECONDS);

            fut = session.getRemote().sendStringByFuture("Thanks for the conversation.");
            fut.get(2,TimeUnit.SECONDS);

            session.close(StatusCode.NORMAL,"I'm done");
        }
        catch (InterruptedException | ExecutionException | TimeoutException t)
        {
        }
    }

    @OnWebSocketMessage
    public void onMessage(String msg)
    {
        System.out.printf("Got msg: %s%n",msg);
    }
}