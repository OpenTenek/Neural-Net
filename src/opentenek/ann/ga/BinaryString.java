package opentenek.ann.ga;

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
    
    public char getBit(int index) 
    {
        if(index < 0 || index >= data.length()) return ' ';
        return data.toCharArray()[index];
    }
    
    public void setBit(int index, int bit) 
    {
        if(index < 0 || index >= data.length()) return;
        if(bit != 0 && bit != 1) return;
        
        char chars[] = data.toCharArray();
        chars[index] = bit > 0 ? '1' : '0';
        data = new String(chars);
    }
    
    public String getData() { return data; }
    
    public String toString() 
    {
        return new String(data).replace('0', '.');
    }
}
