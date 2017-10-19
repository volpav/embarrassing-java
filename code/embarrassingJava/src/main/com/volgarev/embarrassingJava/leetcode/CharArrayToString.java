package com.volgarev.embarrassingJava.leetcode;

import java.io.IOException;
import java.io.StringReader;
import java.util.*;
import java.util.stream.IntStream;

public class CharArrayToString {
    /**
     * Represents a cursor which allows traversing a character sequence.
     */
    private static class Cursor {
        private char current;
        private char[] items;
        private int index = 0;

        /**
         * Initializes a new instance of an object.
         * @param items Character sequence.
         */
        public Cursor(char[] items) {
            this.items = items;
        }

        /**
         * Gets value indicating whether there's next value available.
         * @return Value indicating whether there's next value.
         */
        public boolean hasNext() {
            return (index + 1) < items.length;
        }

        /**
         * Attempts to advance the cursor.
         * @return A value indicating whether cursor advanced.
         */
        public boolean next() {
            boolean assignNext = hasNext();

            if (assignNext) {
                current = items[++index];
            }

            return assignNext;
        }

        /**
         * Gets the current value at the cursor.
         * @return Current value.
         */
        public char getCurrent() {
            return current;
        }
    }

    /**
     * Represents test case execution result.
     */
    private static class TestCaseExecutionResult {
        private final boolean isPassed;
        private final Exception error;

        /**
         * Gets the shortcut to passed test case.
         */
        public static final TestCaseExecutionResult PASSED =
            new TestCaseExecutionResult(true, null);

        /**
         * Gets the shortcut to failed test case.
         */
        public static final TestCaseExecutionResult FAILED =
            new TestCaseExecutionResult(false, null);

        /**
         * Initializes a new instance of an object.
         * @param isPassed Value indicating whether the code passed the test case.
         * @param error An exception that might have occurred during the execution of the test case.
         */
        private TestCaseExecutionResult(boolean isPassed, Exception error) {
            this.isPassed = isPassed;
            this.error = error;
        }

        /**
         * Gets the value indicating whether the code passed the test case.
         * @return The value indicating whether the code passed the test case.
         */
        public boolean getIsPassed() {
            return isPassed;
        }

        /**
         * Gets an exception that might have occurred during the execution of the test case.
         * @return An exception that might have occurred during the execution of the test case.
         */
        public Exception getError() {
            return error;
        }

        /**
         * Gets the string representation of the current object.
         * @return A string representation of the current object.
         */
        @Override
        public String toString() {
            StringBuilder output = new StringBuilder();

            output.append("passed: ");
            output.append(getIsPassed());
            output.append(", error: ");
            output.append(getError());

            return output.toString();
        }

        /**
         * Returns value indicating whether the given object is equal to the current one.
         * @param other Object to compare to.
         * @return Value indicating whether the given object is equal to the current one.
         */
        @Override
        public boolean equals(Object other) {
            if (!(other instanceof TestCaseExecutionResult)) {
                return false;
            }

            TestCaseExecutionResult otherResult = (TestCaseExecutionResult)other;

            return getIsPassed() == otherResult.getIsPassed() &&
                ((getError() == null && otherResult.getError() == null) ||
                    (getError() != null &&
                        otherResult.getError() != null &&
                        getError().equals(otherResult.getError())
                    )
                );
        }

        /**
         * Returns the hash code of the given object.
         * @return Hash code.
         */
        @Override
        public int hashCode() {
            int hash = 1;

            hash = hash * 17 + (getIsPassed() ? 1 : 0);
            hash = hash * 31 + (getError() != null ? getError().hashCode() : 0);

            return hash;
        }

        /**
         * Returns a test case execution result that is failed with the given exception.
         * @param error Exception.
         * @return Test case execution result.
         */
        public static TestCaseExecutionResult failedWithError(Exception error) {
            return new TestCaseExecutionResult(false, error);
        }
    }

    /**
     * Represents a test case.
     */
    private static class TestCase {
        private char[] charArray;
        private String jsonArray;

        /**
         * Initializes a new instance of an object.
         */
        public TestCase() { }

        /**
         * Initializes a new instance of an object.
         * @param charArray Character array.
         * @param jsonArray JSON array literal.
         */
        public TestCase(char[] charArray, String jsonArray) {
            this.charArray = charArray;
            this.jsonArray = jsonArray;
        }

        /**
         * Gets character array.
         * @return Character array.
         */
        public char[] getCharArray() {
            return charArray;
        }

        /**
         * Sets character array.
         * @param value Character array.
         */
        public void setCharArray(char[] value) {
            charArray = Arrays.copyOf(value, value.length);
        }

        /**
         * Gets JSON array literal.
         * @return JSON array literal.
         */
        public String getJsonArray() {
            return jsonArray;
        }

        /**
         * Sets JSON array literal.
         * @param value JSON array literal.
         */
        public void setJsonArray(String value) {
            jsonArray = value;
        }
    }

    /**
     * Gets the list of known escape sequences that need to be escaped.
     */
    private static final Map<Character, String> SPECIAL_CHARACTERS;

    static {
        SPECIAL_CHARACTERS = new HashMap<>();

        SPECIAL_CHARACTERS.put('\b', "\\b");
        SPECIAL_CHARACTERS.put('\t', "\\t");
        SPECIAL_CHARACTERS.put('\n', "\\n");
        SPECIAL_CHARACTERS.put('\f', "\\f");
        SPECIAL_CHARACTERS.put('\r', "\\r");
        SPECIAL_CHARACTERS.put('\\', "\\\\");
        SPECIAL_CHARACTERS.put('"', "\\\"");
    }

    /**
     * Gets the color reset sequence.
     */
    public static final String ANSI_RESET = "\u001B[0m";

    /**
     * Gets the "Red" color sequence.
     */
    public static final String ANSI_RED = "\u001B[31m";

    /**
     * Gets the "Green" color sequence.
     */
    public static final String ANSI_GREEN = "\u001B[32m";

    /**
     * Converts the given character array into JSON array literal.
     * @param param Character array.
     * @return JSON array literal.
     */
    public static String charArrayToString(char[] param) {
        StringBuilder output = new StringBuilder();

        output.append("[");

        if (param != null) {
            for (int i = 0; i < param.length; i++) {
                writeArrayElement(param[i], output);

                if (i < param.length - 1) {
                    output.append(",");
                }
            }
        }

        output.append("]");

        return output.toString();
    }

    /**
     * Appends JSON array item to the end of a given character sequence.
     * @param ch Character to write.
     * @param output Output character sequence.
     */
    private static void writeArrayElement(char ch, StringBuilder output) {
        if (Character.isDigit(ch)) {
            // Digits don't need to be enclosed in double quotes - we'll treat them as numbers.
            output.append(ch);
        } else {
            output.append('"');

            // Escaping the character, if needed.
            writeEscapeSequence(ch, output);

            output.append('"');
        }
    }

    /**
     * Attempts to write escape sequence to the given output buffer.
     * @param ch Character.
     * @param output Output buffer.
     */
    private static void writeEscapeSequence(char ch, StringBuilder output) {
        output.append(SPECIAL_CHARACTERS.containsKey(ch) ? SPECIAL_CHARACTERS.get(ch) : ch);
    }

    /**
     * Converts the given JSON array literal into character array.
     * @param param JSON array literal.
     * @return Character array.
     */
    public static char[] stringToCharArray(String param) {
        StringBuilder output = new StringBuilder();

        if (param != null) {
            Cursor cursor = new Cursor(param.toCharArray());

            while (cursor.hasNext()) {
                readArrayElement(cursor, output);
            }
        }

        return output.toString().toCharArray();
    }

    /**
     * Reads raw value enclosed into the double quotes.
     * @param cursor Cursor.
     * @return Value.
     */
    private static String readQuotedValue(Cursor cursor) {
        StringBuilder output = new StringBuilder();

        char ch = cursor.getCurrent();

        output.append(ch);

        // We have an escape sequence - need to read an extra character.
        if (ch == '\\') {
            cursor.next();
            output.append(cursor.getCurrent());
        }

        // Advancing from the value.
        cursor.next();

        return output.toString()
            .replace("\\\\", "\\")
            .replace("\\\"", "\"");
    }

    /**
     * Attempts to read the given value as escape sequence.
     * @param data Value to read.
     * @return Value with escape sequence properly interpreted.
     */
    private static char readEscapeSequence(String data) {
        // The trick down below doesn't work for backspace (returns just "b").
        if (data.equals("\\b")) {
            return '\b';
        }

        Properties p = new Properties();

        try {
            p.load(new StringReader("key=" + data));
        } catch (IOException e) {
            return '\0';
        }

        String prop = p.getProperty("key");

        return (prop.isEmpty() ? data : prop).charAt(0);
    }

    /**
     * Appends parsed character to the end of a given character sequence.
     * @param cursor Cursor.
     * @param output Output character sequence.
     */
    private static void readArrayElement(Cursor cursor, StringBuilder output) {
        // Skipping to the beginning of the actual value.
        while (cursor.hasNext() && cursor.getCurrent() != '"' &&
            !Character.isDigit(cursor.getCurrent())) {

            cursor.next();
        }

        if (cursor.hasNext()) {
            if (Character.isDigit(cursor.getCurrent())) {
                output.append(cursor.getCurrent());
            } else {
                // Skipping opening double quotes.
                cursor.next();

                // Reading the value enclosed into double quotes.
                String value = readQuotedValue(cursor);

                // Appending the value, interpreting escape sequence.
                output.append(readEscapeSequence(value));
            }

            // Skipping closing double quotes.
            cursor.next();
        }
    }

    /**
     * Represents an entry points into the application.
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        TestCase[] testCases = getAllTestCases();

        int failedTestCases = 0,
            totalTestCases = testCases.length;

        System.out.println(String.format("Executing %d test cases...\n", totalTestCases));

        for (int i = 0; i < testCases.length; i++) {
            if (!executeTestCaseAndPrintResult(testCases[i], i + 1)) {
                failedTestCases++;
            }
        }

        System.out.println(String.format("\nPassed test cases: %d, failed test cases: %d, pass percentage: %.1f%%",
            totalTestCases - failedTestCases, failedTestCases, 100.0 - ((double)failedTestCases * 100 / (double)totalTestCases)));
    }

    /**
     * Returns all test cases.
     * @return Test cases.
     */
    private static TestCase[] getAllTestCases() {
        return new TestCase[] {
            new TestCase(new char[] { }, "[]"),
            new TestCase(new char[] { }, " [  ]   "),
            new TestCase(new char[] { '1', '2'}, "[1,2]"),
            new TestCase(new char[] { 'a' }, " [  \"a\"  ]     "),
            new TestCase(new char[] { 'a', 'b', 'c' }, "[\"a\",\"b\",\"c\"]"),
            new TestCase(new char[] { '\'', '\"', 'c'}, "[\"'\",\"\\\"\",\"c\"]"),
            new TestCase(new char[] { 'T', 'e', '!', '\'' }, "[\"T\",\"e\",\"!\",\"'\"]"),
            new TestCase(new char[] { '1', 'A', '2', 'b', '\n', '3'}, "[1,  \"A\",2,    \"b\", \"\\n\",    3  ]"),
            new TestCase(new char[] { '\n', '\t', '\'', '\"', '\\' }, "[\"\\n\",\"\\t\",\"'\",\"\\\"\",\"\\\\\"]"),
            new TestCase(new char[] { '"', '\\', '/', '\b', '\f', '\n', '\r', '\t' }, "[\"\\\"\",\"\\\\\",\"/\",\"\\b\",\"\\f\",\"\\n\",\"\\r\",\"\\t\"]")
        };
    }

    /**
     * Executes the given test case and prints the result to standard output.
     * @param testCase Test case.
     * @param testCaseId Test case Id.
     */
    private static boolean executeTestCaseAndPrintResult(TestCase testCase, int testCaseId) {
        boolean successOneWay = printTestCaseResult(
            executeTestCase(testCase.getCharArray(), testCase.getJsonArray()),
            testCaseId,
            "array to JSON"
        );

        boolean successOppositeWay = printTestCaseResult(
            executeTestCase(testCase.getJsonArray(), testCase.getCharArray()),
            testCaseId,
            "JSON to array"
        );

        return successOneWay && successOppositeWay;
    }

    /**
     * Prints the given test case result to the standard output.
     * @param result Result.
     * @param testCaseId Test case Id.
     * @param description Test case description.
     */
    private static boolean printTestCaseResult(TestCaseExecutionResult result, int testCaseId, String description) {
        boolean testCasePassed = result.getIsPassed();

        if (testCasePassed) {
            System.out.println(String.format("Test case #%d (%s): %sPASSED%s", testCaseId, description, ANSI_GREEN, ANSI_RESET));
        } else {
            System.out.println(String.format("Test case #%d (%s): %sFAILED%s", testCaseId, description, ANSI_RED, ANSI_RESET));

            if (result.getError() != null) {
                System.out.println(String.format("    Error: %s", result.getError().toString()));
            }
        }

        return testCasePassed;
    }

    /**
     * Executes the given test case and returns execution result.
     * @param input Input.
     * @param expectedOutput Expected output.
     * @return Execution result.
     */
    private static TestCaseExecutionResult executeTestCase(char[] input, String expectedOutput) {
        try {
            String actualOutput = charArrayToString(input);
            String expectedOutputSanitized = sanitizeExpectedJsonLiteral(expectedOutput);

            // Checking one direction (array to JSON).
            if (!expectedOutputSanitized.equals(actualOutput)) {
                return TestCaseExecutionResult.FAILED;
            }
        } catch (Exception e) {
            return TestCaseExecutionResult.failedWithError(e);
        }

        return TestCaseExecutionResult.PASSED;
    }

    /**
     * Executes the given test case and returns execution result.
     * @param input Input.
     * @param expectedOutput Expected output.
     * @return Execution result.
     */
    private static TestCaseExecutionResult executeTestCase(String input, char[] expectedOutput) {
        try {
            char[] actualOutput = stringToCharArray(input);

            if (actualOutput.length != expectedOutput.length ||
                    !IntStream.range(0, expectedOutput.length).allMatch(i -> expectedOutput[i] == actualOutput[i])) {

                return TestCaseExecutionResult.FAILED;
            }
        } catch (Exception e) {
            return TestCaseExecutionResult.failedWithError(e);
        }

        return TestCaseExecutionResult.PASSED;
    }

    /**
     * Sanitizes the expected JSON literal to match the output that would have been produced by deserialization.
     * @param jsonLiteral JSON literal to sanitize.
     * @return Sanitized JSON literal.
     */
    private static String sanitizeExpectedJsonLiteral(String jsonLiteral) {
        // Removing leading whitespace characters.
        jsonLiteral = jsonLiteral.replaceAll("^\\s+\\[", "[");

        // Removing trailing whitespace characters.
        jsonLiteral = jsonLiteral.replaceAll("]\\s+$", "]");

        // Removing leading whitespace right after array start.
        jsonLiteral = jsonLiteral.replaceAll("^\\[\\s+", "[");

        // Removing trailing whitespace right before array end.
        jsonLiteral = jsonLiteral.replaceAll("\\s+]$", "]");

        // Removing whitespace characters inside an empty array.
        jsonLiteral = jsonLiteral.replaceAll("^\\[\\s+]$", "[]");

        // Replacing spaces between array elements.
        jsonLiteral = jsonLiteral.replaceAll("(\\[|,|\"|[0-9])\\s+(]|,|\"|[0-9])", "$1$2");

        return jsonLiteral;
    }
}
