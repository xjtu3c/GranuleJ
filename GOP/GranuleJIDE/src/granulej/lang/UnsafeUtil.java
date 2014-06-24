package granulej.lang;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

public class UnsafeUtil {
	 
	  @SuppressWarnings("ALL")
	  private static final Unsafe unsafe;
	  private static final int BYTES_OFFSET;
	
	  
	   protected long index = -1;
	   protected long start = 0;
	   protected long position = 0;
		 
		 static{		
		        try {
		            @SuppressWarnings("ALL")
		            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
		            theUnsafe.setAccessible(true);
		            unsafe = (Unsafe) theUnsafe.get(null);
		            BYTES_OFFSET = unsafe.arrayBaseOffset(byte[].class);
		        } catch (Exception e) {
		            throw new AssertionError(e);
		        }
		  }

		public static Unsafe getUnsafe() {
			return unsafe;
		}
		
		public byte readByte() {
		        return unsafe.getByte(position++);
		}

		public byte readByte(int offset) {
		        return unsafe.getByte(start + offset);
		 }

		public void readFully(byte[] b, int off, int len) {
		        unsafe.copyMemory(null, position, b, BYTES_OFFSET + off, len);
		        position += len;
		}

		public short readShort() {
		        short s = unsafe.getShort(position);
		        position += 2;
		        return s;
		 }

		 public short readShort(int offset) {
		        return unsafe.getShort(start + offset);
		  }

		 public char readChar() {
		        char ch = unsafe.getChar(position);
		        position += 2;
		        return ch;
		 }


		 public char readChar(int offset) {
		        return unsafe.getChar(start + offset);
		 }

		
		 public int readInt() {
		        int i = unsafe.getInt(position);
		        position += 4;
		        return i;
		 }

		
		 public int readInt(int offset) {
		        return unsafe.getInt(start + offset);
		 }

		 public long readLong() {
		       long l = unsafe.getLong(position);
		       position += 8;
		       return l;
		 }

		 public long readLong(int offset) {
		        return unsafe.getLong(start + offset);
		  }

	      public float readFloat() {
		        float f = unsafe.getFloat(position);
		        position += 4;
		        return f;
		    }


		    public float readFloat(int offset) {
		        return unsafe.getFloat(start + offset);
		    }

		    public double readDouble() {
		        double d = unsafe.getDouble(position);
		        position += 8;
		        return d;
		    }
		
		    public double readDouble(int offset) {
		        return unsafe.getDouble(start + offset);
		    }

		  
		    public void write(int b) {
		        unsafe.putByte(position++, (byte) b);
		    }
		   
		    public void write(int offset, int b) {
		        unsafe.putByte(start + offset, (byte) b);
		    }

		   
		    public void write(int offset, byte[] b) {
		        unsafe.copyMemory(b, BYTES_OFFSET, null, position, b.length);
		        position += b.length;
		    }
		  
		    public void write(byte[] b, int off, int len) {
		        unsafe.copyMemory(b, BYTES_OFFSET + off, null, position, len);
		        position += len;
		    }
		   
		    public void writeShort(int v) {
		        unsafe.putShort(position, (short) v);
		        position += 2;
		    }

		    public void writeShort(int offset, int v) {
		        unsafe.putShort(start + offset, (short) v);
		    }

		    public void writeChar(int v) {
		        unsafe.putChar(position, (char) v);
		        position += 2;
		    }

		    public void writeChar(int offset, int v) {
		        unsafe.putChar(start + offset, (char) v);
		    }

		    public void writeInt(int v) {
		        unsafe.putInt(position, v);
		        position += 4;
		    }

		    public void writeInt(int offset, int v) {
		        unsafe.putInt(start + offset, v);
		    }

		    public void writeLong(long v) {
		        unsafe.putLong(position, v);
		        position += 8;
		    }

		    public void writeLong(int offset, long v) {
		        unsafe.putLong(start + offset, v);
		    }

		    public void writeFloat(float v) {
		        unsafe.putFloat(position, v);
		        position += 4;
		    }

		    public void writeFloat(int offset, float v) {
		        unsafe.putFloat(start + offset, v);
		    }

		    public void writeDouble(double v) {
		        unsafe.putDouble(position, v);
		        position += 8;
		    }

		    public void writeDouble(int offset, double v) {
		        unsafe.putDouble(start + offset, v);
		    }

		    public int read(byte[] b, int off, int len) {
		        if (len < 0 || off < 0 || off + len > b.length)
		            throw new IllegalArgumentException();
		        //if (len > remaining())
		           // len = remaining();
		        unsafe.copyMemory(null, position, b, BYTES_OFFSET + off, len);
		        position += len;
		        return len;
		    }		
		
}
