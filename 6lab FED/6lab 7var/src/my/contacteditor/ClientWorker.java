package my.contacteditor;

import java.net.*;

public class ClientWorker implements Runnable {

    @Override
    public void run() {
        try {
            DatagramSocket socket = new DatagramSocket(6000);

            while (true) {
                byte[] buffer = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

                socket.receive(packet);

                String msg = new String(packet.getData(), 0, packet.getLength());
                String[] parts = msg.split(";");

                double lower = Double.parseDouble(parts[0]);
                double upper = Double.parseDouble(parts[1]);
                double range = Double.parseDouble(parts[2]);

                // ===== ТВОЙ МНОГОПОТОЧНЫЙ КОД =====
                int THREADS = 7;

                double step = (upper - lower) / THREADS;

                Thread[] threads = new Thread[THREADS];
                IntegralTask[] tasks = new IntegralTask[THREADS];

                double start = lower;

                for (int i = 0; i < THREADS; i++) {
                    double end = (i == THREADS - 1) ? upper : start + step;

                    tasks[i] = new IntegralTask(start, end, range);
                    threads[i] = new Thread(tasks[i]);
                    threads[i].start();

                    start = end;
                }

                double total = 0;

                for (int i = 0; i < THREADS; i++) {
                    threads[i].join();
                    total += tasks[i].getResult();
                }

                // ===== отправка результата =====
                String result = String.valueOf(total);
                byte[] sendData = result.getBytes();

                DatagramPacket response = new DatagramPacket(
                        sendData,
                        sendData.length,
                        packet.getAddress(),
                        packet.getPort()
                );

                socket.send(response);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}