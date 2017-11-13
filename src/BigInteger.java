public class BigInteger {
    byte[] data;
    int length;
    final int MAX_LEN = 1000002;
    boolean isNegative;
    boolean isEmpty = true;

    private static String randomNum(int len) {
        StringBuffer s = new StringBuffer("");
        while(len>0)
        {
            s.append(String.valueOf(Math.round(Math.random()*10-0.5)));
            len--;
        }
        String res = new String(s);
        return res;
        
    }
    
    private void freshDigit() {
        int count = MAX_LEN;
        while (data[count] == 0 && count > 0)
            count--;
        length = count;
        if (count == 0 && !isEmpty)
            length = 1;
    }

    public BigInteger(String num) {
        // TODO Auto-generated constructor stub
        length = num.length();
        if (length > MAX_LEN)
            return;
        if (num != null)
            isEmpty = false;
        data = new byte[MAX_LEN + 1];
        if (num.charAt(0) == '-') {
            isNegative = true;
            for (int i = length - 1; i > 0; i--)
                data[i] = (byte) (num.charAt(length - i) - '0');
        } else {
            for (int i = length; i > 0; i--)
                data[i] = (byte) (num.charAt(length - i) - '0');
        }
    }

    public boolean isEqual(BigInteger num) {
        int count = 0;
        while (this.data[count + 1] == num.data[count + 1]) {
            if (count >= this.length && count >= num.length)
                return true;
            count++;
        }
        return false;
    }

    public boolean isLarger(BigInteger num) {
        int count = MAX_LEN;
        if (isEqual(num))
            return false;
        while (this.data[count] == 0 && num.data[count] == 0)
            count--;
        if (num.isNegative && this.isNegative)
            return this.data[count] < num.data[count] ? true : false;
        else if (num.isNegative || this.isNegative)
            return num.isNegative ? true : false;
        return this.data[count] > num.data[count] ? true : false;
    }

    public boolean isSmaller(BigInteger num) {
        if (isEqual(num))
            return false;
        else
            return !this.isLarger(num);
    }

    public BigInteger multiply(BigInteger num) {
        BigInteger result = new BigInteger("0");
        for(int i = 1;i<=this.length;i++)
        {
            for(int j = 1;j<=num.length;j++)
            {
                result.data[i+j-1] += (byte) (this.data[i]*num.data[j]%10);
                result.data[i+j] += (byte) (this.data[i]*num.data[j]/10);
                if(result.data[i+j-1]>=10)
                {
                    result.data[i+j] += result.data[i+j-1]/10;
                    result.data[i+j-1] %= 10;
                }
            }
        }
        result.freshDigit();
        return result;
    }
    
    public BigInteger add(BigInteger num) {
        int digitCount = num.length > this.length ? num.length : this.length;
        if (num.isNegative)
            return this.substract(num);
        else if (this.isNegative)
            return num.substract(this);

        BigInteger result = new BigInteger("0");
        for (int i = 1; i <= digitCount; i++) {
            byte B = num.data[i], A = this.data[i];
            byte carry = (byte) ((A + B) / 10);
            byte present = (byte) ((A + B) % 10);
            result.data[i + 1] += carry;
            result.data[i] += present;
        }
        result.freshDigit();
        return result;

    }

    public BigInteger substract(BigInteger num) {
        int digitCount = num.length > this.length ? num.length : this.length;
        BigInteger result = new BigInteger("0");
        if (this.isSmaller(num))
            result.isNegative = true;
        for (int i = 1; i <= digitCount; i++) {
            byte A, B;
            if (result.isNegative) {
                A = num.data[i];
                B = this.data[i];
            } else {
                A = this.data[i];
                B = num.data[i];
            }
            byte carry = (byte) (result.data[i] + A - B);
            if (carry < 0) {
                result.data[i + 1]--;
                result.data[i] = (byte) (carry + 10);
            } else
                result.data[i] = carry;
        }
        result.freshDigit();
        return result;
    }

    public String toString() {
        String res = "";
        StringBuffer s = new StringBuffer(res);
        if (this.isNegative)
            s.append('-');
        for (int i = this.length; i > 0; i--)
            s.append((char) (data[i] + '0'));
        res = s.toString();
        return res;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String C = randomNum(3);
        String D = randomNum(3);
        long start = System.currentTimeMillis();
        BigInteger A = new BigInteger(C);
        System.out.println(C);
        BigInteger B = new BigInteger(D);
        System.out.println(D);
        B = A.multiply(B);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        System.out.print(B);
    }

}
