/*
 * BinUtils - access various binary formats from Java
 *
 * (C) Copyright 2018 - Matthias Bläsing - mblaesing@doppel-helix.eu
 *
 * Licensed under Apache License v2.
 */
package nl.lxtreme.binutils.elf;

import java.math.BigInteger;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ArmAeabiAttributesTag<T> {

    private final int value;
    private final String name;
    private final ArmParameterType<T> parameterType;

    public ArmAeabiAttributesTag(int value, String name, ArmParameterType<T> parameterType) {
        this.value = value;
        this.name = name;
        this.parameterType = parameterType;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public ArmParameterType getParameterType() {
        return parameterType;
    }

    @Override
    public String toString() {
        return name + " (" + value + ")";
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.value;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ArmAeabiAttributesTag other = (ArmAeabiAttributesTag) obj;
        if (this.value != other.value) {
            return false;
        }
        return true;
    }

    private static final List<ArmAeabiAttributesTag> tags = new LinkedList<ArmAeabiAttributesTag>();
    private static final Map<Integer, ArmAeabiAttributesTag> valueMap = new HashMap<Integer, ArmAeabiAttributesTag>();
    private static final Map<String, ArmAeabiAttributesTag> nameMap = new HashMap<String, ArmAeabiAttributesTag>();

    // Enumerated from ARM IHI 0045E, 2.5 Attributes summary and history
    public static final ArmAeabiAttributesTag<Integer> File = addTag(1, "File", ArmParameterType.UINT32);
    public static final ArmAeabiAttributesTag<Integer> Section = addTag(2, "Section", ArmParameterType.UINT32);
    public static final ArmAeabiAttributesTag<Integer> Symbol = addTag(3, "Symbol", ArmParameterType.UINT32);
    public static final ArmAeabiAttributesTag<String> CPU_raw_name = addTag(4, "CPU_raw_name", ArmParameterType.NTBS);
    public static final ArmAeabiAttributesTag<String> CPU_name = addTag(5, "CPU_name", ArmParameterType.NTBS);
    public static final ArmAeabiAttributesTag<BigInteger> CPU_arch = addTag(6, "CPU_arch", ArmParameterType.ULEB128);
    public static final ArmAeabiAttributesTag<BigInteger> CPU_arch_profile = addTag(7, "CPU_arch_profile", ArmParameterType.ULEB128);
    public static final ArmAeabiAttributesTag<BigInteger> ARM_ISA_use = addTag(8, "ARM_ISA_use", ArmParameterType.ULEB128);
    public static final ArmAeabiAttributesTag<BigInteger> THUMB_ISA_use= addTag(9, "THUMB_ISA_use", ArmParameterType.ULEB128);
    public static final ArmAeabiAttributesTag<BigInteger> FP_arch = addTag(10, "FP_arch", ArmParameterType.ULEB128);
    public static final ArmAeabiAttributesTag<BigInteger> WMMX_arch = addTag(11, "WMMX_arch", ArmParameterType.ULEB128);
    public static final ArmAeabiAttributesTag<BigInteger> Advanced_SIMD_arch = addTag(12, "Advanced_SIMD_arch", ArmParameterType.ULEB128);
    public static final ArmAeabiAttributesTag<BigInteger> PCS_config = addTag(13, "PCS_config", ArmParameterType.ULEB128);
    public static final ArmAeabiAttributesTag<BigInteger> ABI_PCS_R9_use = addTag(14, "ABI_PCS_R9_use", ArmParameterType.ULEB128);
    public static final ArmAeabiAttributesTag<BigInteger> ABI_PCS_RW_data = addTag(15, "ABI_PCS_RW_data", ArmParameterType.ULEB128);
    public static final ArmAeabiAttributesTag<BigInteger> ABI_PCS_RO_data = addTag(16, "ABI_PCS_RO_data", ArmParameterType.ULEB128);
    public static final ArmAeabiAttributesTag<BigInteger> ABI_PCS_GOT_use = addTag(17, "ABI_PCS_GOT_use", ArmParameterType.ULEB128);
    public static final ArmAeabiAttributesTag<BigInteger> ABI_PCS_wchar_t = addTag(18, "ABI_PCS_wchar_t", ArmParameterType.ULEB128);
    public static final ArmAeabiAttributesTag<BigInteger> ABI_FP_rounding = addTag(19, "ABI_FP_rounding", ArmParameterType.ULEB128);
    public static final ArmAeabiAttributesTag<BigInteger> ABI_FP_denormal = addTag(20, "ABI_FP_denormal", ArmParameterType.ULEB128);
    public static final ArmAeabiAttributesTag<BigInteger> ABI_FP_exceptions = addTag(21, "ABI_FP_exceptions", ArmParameterType.ULEB128);
    public static final ArmAeabiAttributesTag<BigInteger> ABI_FP_user_exceptions = addTag(22, "ABI_FP_user_exceptions", ArmParameterType.ULEB128);
    public static final ArmAeabiAttributesTag<BigInteger> ABI_FP_number_model = addTag(23, "ABI_FP_number_model", ArmParameterType.ULEB128);
    public static final ArmAeabiAttributesTag<BigInteger> ABI_align_needed= addTag(24, "ABI_align_needed", ArmParameterType.ULEB128);
    public static final ArmAeabiAttributesTag<BigInteger> ABI_align8_preserved = addTag(25, "ABI_align8_preserved", ArmParameterType.ULEB128);
    public static final ArmAeabiAttributesTag<BigInteger> ABI_enum_size= addTag(26, "ABI_enum_size", ArmParameterType.ULEB128);
    public static final ArmAeabiAttributesTag<BigInteger> ABI_HardFP_use = addTag(27, "ABI_HardFP_use", ArmParameterType.ULEB128);
    public static final ArmAeabiAttributesTag<BigInteger> ABI_VFP_args = addTag(28, "ABI_VFP_args", ArmParameterType.ULEB128);
    public static final ArmAeabiAttributesTag<BigInteger> ABI_WMMX_args = addTag(29, "ABI_WMMX_args", ArmParameterType.ULEB128);
    public static final ArmAeabiAttributesTag<BigInteger> ABI_optimization_goals = addTag(30, "ABI_optimization_goals", ArmParameterType.ULEB128);
    public static final ArmAeabiAttributesTag<BigInteger> ABI_FP_optimization_goals = addTag(31, "ABI_FP_optimization_goals", ArmParameterType.ULEB128);
    public static final ArmAeabiAttributesTag<String> compatibility = addTag(32, "compatibility", ArmParameterType.NTBS);
    public static final ArmAeabiAttributesTag<BigInteger> CPU_unaligned_access = addTag(34, "CPU_unaligned_access", ArmParameterType.ULEB128);
    public static final ArmAeabiAttributesTag<BigInteger> FP_HP_extension = addTag(36, "FP_HP_extension", ArmParameterType.ULEB128);
    public static final ArmAeabiAttributesTag<BigInteger> ABI_FP_16bit_format = addTag(38, "ABI_FP_16bit_format", ArmParameterType.ULEB128);
    public static final ArmAeabiAttributesTag<BigInteger> MPextension_use = addTag(42, "MPextension_use", ArmParameterType.ULEB128);
    public static final ArmAeabiAttributesTag<BigInteger> DIV_use = addTag(44, "DIV_use", ArmParameterType.ULEB128);
    public static final ArmAeabiAttributesTag<BigInteger> nodefaults = addTag(64, "nodefaults", ArmParameterType.ULEB128);
    public static final ArmAeabiAttributesTag<String> also_compatible_with = addTag(65, "also_compatible_with", ArmParameterType.NTBS);
    public static final ArmAeabiAttributesTag<String> conformance = addTag(67, "conformance", ArmParameterType.NTBS);
    public static final ArmAeabiAttributesTag<BigInteger> T2EE_use = addTag(66, "T2EE_use", ArmParameterType.ULEB128);
    public static final ArmAeabiAttributesTag<BigInteger> Virtualization_use = addTag(68, "Virtualization_use", ArmParameterType.ULEB128);
    public static final ArmAeabiAttributesTag<BigInteger> MPextension_use2 = addTag(70, "MPextension_use", ArmParameterType.ULEB128);

    private static <S> ArmAeabiAttributesTag<S> addTag(int value, String name, ArmParameterType<S> type) {
        ArmAeabiAttributesTag tag = new ArmAeabiAttributesTag(value, name, type);
        
        if (!valueMap.containsKey(tag.getValue())) {
            valueMap.put(tag.getValue(), tag);
        }
        if (!nameMap.containsKey(tag.getName())) {
            nameMap.put(tag.getName(), tag);
        }
        tags.add(tag);
        return tag;
    }

    public static List<ArmAeabiAttributesTag> getTags() {
        return Collections.unmodifiableList(tags);
    }

    public static ArmAeabiAttributesTag getByName(String name) {
        return nameMap.get(name);
    }

    public static ArmAeabiAttributesTag getByValue(int value) {
        if(valueMap.containsKey(value)) {
            return valueMap.get(value);
        } else {
            ArmAeabiAttributesTag pseudoTag = new ArmAeabiAttributesTag(value, "Unknown " + value, getParameterType(value));
            return pseudoTag;
        }
    }

    private static ArmParameterType getParameterType(int value) {
        // ARM IHI 0045E, 2.2.6 Coding extensibility and compatibility
        ArmAeabiAttributesTag tag = getByValue(value);
        if (tag == null) {
            if ((value % 2) == 0) {
                return ArmParameterType.ULEB128;
            } else {
                return ArmParameterType.NTBS;
            }
        } else {
            return tag.getParameterType();
        }
    }
}
