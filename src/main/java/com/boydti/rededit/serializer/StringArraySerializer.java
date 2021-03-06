package com.boydti.rededit.serializer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class StringArraySerializer implements Serializer<String[]> {
    @Override
    public void write(DataOutputStream dout, String[] values) throws IOException {
        dout.writeInt(values.length);
        for (String value : values) {
            boolean present = value != null;
            dout.writeBoolean(present);
            if (present) {
                dout.writeUTF(value);
            }
        }
    }

    @Override
    public String[] read(DataInputStream din) throws IOException {
        String[] values = new String[din.readInt()];
        for (int i = 0; i < values.length; i++) {
            if (din.readBoolean()) values[i] = din.readUTF();
        }
        return values;
    }
}
