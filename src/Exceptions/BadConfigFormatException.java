/**Kira Combs
 * Nicola Hetrick
 * 10/11/12
 */
package Exceptions;

//generic exception class. allows writing of appropriate message depending on issue with configuration file
public class BadConfigFormatException extends Exception{
	public BadConfigFormatException(String message) {
		super(message);
	}
}
