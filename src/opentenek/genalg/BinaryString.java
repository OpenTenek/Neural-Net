package opentenek.genalg;

public class BinaryString
{
    private String data;
    
    public BinaryString(int length) 
    {
        data = "";
        for(int i = 0; i < length; i++) 
        {
            data += Math.random() < 0.5 ? '0' : '1';
        }
    }
    
    public int getBit(int index) 
    {
        if(index < 0 || index >= data.length()) return -1;
        return data.toCharArray()[index] == '0' ? 0 : 1;
    }
    
    public void setBit(int index, int bit) 
    {
        if(index < 0 || index >= data.length()) return;
        char chars[] = data.toCharArray();
        chars[index] = bit == 0 ? '0' : '1';
        data = new String(chars);
    }
    
    public String getData() { return data; }
    
    public String toString() 
    {
        return new String(data).replace('0', '.');
    }
    
    public int size() 
    {
        return data.length();
    }
}
