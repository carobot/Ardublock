package com.ardublock.translator.block.carobot;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
// import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class CarobotMotorBackwardBlock extends TranslatorBlock {

  public CarobotMotorBackwardBlock(Long blockId, Translator translator, String codePrefix,
      String codeSuffix, String label) {
    super(blockId, translator, codePrefix, codeSuffix, label);
  }

  /**
   * Translates block to code.
   */
  public String toCode() throws SocketNullException, SubroutineNotDeclaredException {
    translator.addHeaderFile("CAROBOT_SwissCHEESE.h");
    TranslatorBlock motorBlock = this.getRequiredTranslatorBlockAtSocket(0);
    String motorNumber = motorBlock.toCode();
    String number = motorNumber.substring(1, 2);
    translator.addDefinitionCommand("SCMotor motor" + number + "(" + motorNumber + ");");

    return "motor" + number + ".speed(-255);\n";
  }

}
