package deepdive.final_transient_volatile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

// tag::class[]
class Transient implements Serializable {
    private final String value1;
    private transient String value2;   // Transient object are not serialized.

    public Transient(String value1, String value2) {
        this.value1 = value1;
        this.value2 = value2;
    }

    @Override
    public String toString() {
        return "Transient{" +
            "value1='" + value1 + '\'' +
            ", value2='" + value2 + '\'' +
            '}';
    }
// end::class[]

// tag::main[]
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        final Transient aTransient = new Transient("Hallo", "Welt");

        // Value 2 is set
        System.out.println(aTransient);

        // Serializing the Transient object to a byte array.
        final ByteArrayOutputStream stream = new ByteArrayOutputStream();
        final ObjectOutputStream out = new ObjectOutputStream(stream);
        out.writeObject(aTransient);
        out.close();

        byte[] serializedTransient = stream.toByteArray();

        // Deserializing the byte array back to the Transient object
        ByteArrayInputStream stream1 = new ByteArrayInputStream(serializedTransient);
        ObjectInputStream in = new ObjectInputStream(stream1);
        Transient deserialized = (Transient) in.readObject();

        // Value 2 is null
        System.out.println(deserialized);
    }
// end::main[]
}
