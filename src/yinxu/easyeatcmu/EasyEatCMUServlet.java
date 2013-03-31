package yinxu.easyeatcmu;

import java.io.IOException;
import java.math.BigInteger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class EasyEatCMUServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
	        String result = null;
	        String xStr = request.getParameter("x");
	        String yStr = request.getParameter("y");
	        String calcType = request.getParameter("calcOp");
	        
	        //check if the input in empty
	        //if (!("".equals(xStr) || "".equals(yStr))) {
	        if (!(xStr==null || yStr==null)) {
	            BigInteger bigX = new BigInteger(xStr);
	            BigInteger bigY = new BigInteger(yStr);
	            result = calc(bigX, bigY, calcType);
	        }
	        else{
	        result = "Error: Zero length BigInteger.";
	        }
	        
	        //use JSP generate returned page
	        request.setAttribute("answer", result);
	        request.setAttribute("x", xStr);
	        request.setAttribute("y", yStr);
	        request.setAttribute("op", calcType);
	        RequestDispatcher view = request.getRequestDispatcher("index.jsp");
	        view.forward(request, response);
	}
	
	//calculate the result of six kinds of arithmetic operations.
    public String calc(BigInteger x, BigInteger y, String calcType){
        BigInteger result;
        if(calcType.equals("add")){
            result = x.add(y);
            return result.toString();
        }
        else if(calcType.equals("multiply")){
            result = x.multiply(y);
            return result.toString();
        }
        else if (calcType.equals("relativelyPrime")) {
            //negative numbers don't have relatively prime
            if (x.compareTo(BigInteger.ZERO) >= 0
                    & y.compareTo(BigInteger.ZERO) >= 0) {
                if (x.gcd(y).equals(BigInteger.ONE)) {
                    return "x and y are relatively prime.";
                } else {
                    return "x and y are not relatively prime.";
                }
            } else {
                return "x and y are not relatively prime.";
            }
        }
        else if(calcType.equals("mod")){
            try{
            result = x.mod(y);
            return result.toString();
            }catch(ArithmeticException e){
                return e.toString(); //catch and return the exception
            }
        }
        else if (calcType.equals("modInverse")){
            try{
            result = x.modInverse(y);
            return result.toString();
            }
            catch(ArithmeticException e){
                return e.toString(); //catch and return the exception
            }
        }
        else if (calcType.equals("power")) { 
            int yInt = y.intValue();
            try{
            result = x.pow(yInt);
            return result.toString();
            }catch(ArithmeticException e){
                return e.toString(); //catch and return the exception
            }
        }
        else{
            return "undefined operation.";
        }
            
    }
}
