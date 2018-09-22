/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.sqlbuilder.iface;

import com.provys.provysdb.call.BindValue;
import java.util.List;

/**
 * CodeBuilder is object that allows to build SQL statement (string) from lines.
 * It is used internally by SQLBuilder to build SQL text from structured query
 * defined by SqlBuilder.
 * 
 * @author stehlik
 */
public interface CodeBuilder {

    /**
     * Adds line of text to already existing code.
     * 
     * @param line contains text to be added (without newline char)
     * @return returns self to support chaining
     */
    public CodeBuilder addLine(String line);

    /**
     * Adds piece of text to already existing code.
     * 
     * @param text contains text to be added
     * @return returns self to support chaining
     */
    public CodeBuilder add(String text);

    /**
     * Increases ident level (using spaces).
     * 
     * @param chars is number of additional characters, must be positive number
     * @return self to allow chaining
     */
    public CodeBuilder increaseIdentBy(int chars);

    /**
     * Decrease ident level by cutting given number of characters from the end.
     * 
     * @param chars is number of characters to be removed
     * @return self to allow chaining
     */
    public CodeBuilder decreaseIdentBy(int chars);

    /**
     * Decrease ident level to specified value.
     * Used mostly when identation is increased by string with variable length
     * 
     * @param chars number of characters that should be left in identation
     * @return self to allow chaining
     */
    public CodeBuilder decreaseIdentTo(int chars);

    /**
     * Sets ident to given number of space characters.
     * Unlike increaseIdent/decreaseIdent methods, this method sets completely
     * new identation and ignores current setting.
     * 
     * @param chars number of spaces that should form ident
     * @return self to allow chaining
     */
    public CodeBuilder setIdent(int chars);

    /**
     * Gets current ident string.
     * 
     * @return current ident
     */
    public String getIdent();

    /**
     * Sets ident to given string.
     * 
     * @param ident is new ident string
     * @return self to support chaining
     */
    public CodeBuilder setIdent(String ident);

    /**
     * Sets ident to given string, left padded to specified length.
     * 
     * @param ident is new ident string
     * @param chars is length to which ident should be left padded with spaces
     * @return self to support chaining
     */
    public CodeBuilder setIdent(String ident, int chars);

    /**
     * Sets ident and first ident to given strings.
     * 
     * @param firstIdent is new ident for the first line
     * @param regularIdent is new ident valid from the second line on
     * @return self to support chaining
     */
    public CodeBuilder setIdent(String firstIdent, String regularIdent);

    /**
     * Sets ident and first ident to given strings, left padded to required
     * length.
     * 
     * @param firstIdent is new ident for the first line
     * @param regularIdent is new ident valid from the second line on
     * @param chars is required ident length - supplied strings will be left
     * padded with spaces to reach this length
     * @return self to support chaining
     */
    public CodeBuilder setIdent(String firstIdent, String regularIdent
            , int chars);

    /**
     * Adds given text to identation.
     * 
     * @param text is string to be appended to ident
     * @return self to support chaining
     */
    public CodeBuilder addIdent(String text);

    /**
     * Add given text to identation, using different ident on the first line.
     * Often used with list of parameters and such, as first line is spaces
     * only, starting from second line, comma is used. Any subsequent operation
     * ident will cancel first ident
     * 
     * @param firstIdent is additional text appended to ident on the first line
     * @param regularIdent is additiona ltext added to ident starting from
     * second line
     * @return self to support chaining
     */
    public CodeBuilder addIdent(String firstIdent, String regularIdent);

    /**
     * Set identation to specified text temporarily.
     * Temporary ident means that it is possible to return to previous setting
     * using removeTempIdent method instead of decreaseIdent
     * 
     * @param ident is new ident to be used
     * @return self to support chaining
     */
    public CodeBuilder setTempIdent(String ident);

    /**
     * Set temporary text to identation, using different ident on the first
     * line.
     * Often used with list of parameters and such, as first line is spaces
     * only, starting from second line, comma is used. Temporary ident means
     * that it is possible to return to previous setting using removeTempIdent
     * method instead of decreaseIdent
     * 
     * @param firstIdent is additional text appended to ident on the first line
     * @param regularIdent is additiona ltext added to ident starting from
     * second line
     * @return self to support chaining
     */
    public CodeBuilder setTempIdent(String firstIdent, String regularIdent);

    /**
     * Adds temporary text to identation.
     * Temporary ident means that it is possible to return to previous setting
     * using removeTempIdent method instead of decreaseIdent
     * 
     * @param text is string to be appended to ident
     * @return self to support chaining
     */
    public CodeBuilder addTempIdent(String text);

    /**
     * Add temporary text to identation, using different ident on the first line.
     * Often used with list of parameters and such, as first line is spaces
     * only, starting from second line, comma is used. Temporary ident means
     * that it is possible to return to previous setting using removeTempIdent
     * method instead of decreaseIdent
     * 
     * @param firstIdent is additional text appended to ident on the first line
     * @param regularIdent is additiona ltext added to ident starting from
     * second line
     * @return self to support chaining
     */
    public CodeBuilder addTempIdent(String firstIdent, String regularIdent);

    /**
     * Removes last added temporary identation.
     * 
     * @return self to allow chaining.
     */
    public CodeBuilder removeTempIdent();
    
    /**
     * Adds bind value to list of binds.
     * If bind already exists but has different value or type, exception is
     * raised.
     * 
     * @param bindValue is bind value to be added.
     * @return self to support chaining
     */
    public CodeBuilder addBind(BindValue bindValue);

    /**
     * Adds list of bind values to list of binds.
     * If bind already exists but has different value or type, exception is
     * raised.
     * 
     * @param bindValues is list of binds to be added.
     * @return self to support chaining
     */
    public CodeBuilder addBind(List<BindValue> bindValues);
    
    /**
     * Adds bind value; if name is already used, appends number to its end
     * to make it unique.
     * 
     * @param bindValue is bind to be added; it might be adjusted if such bind
     * already exists
     * @param allowReuse defines if existing bind can be reused if it has the
     * same data type and value
     * @return bind name (after adjustment if name was not unique)
     */
    public String addUniqueBind(BindValue bindValue, boolean allowReuse);
    
    public String getCode();
    
    public List<BindValue> getBindValues();
}
    