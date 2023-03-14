package extendedui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import extendedui.interfaces.delegates.FuncT2;
import extendedui.ui.TextureCache;

import java.util.HashMap;
import java.util.List;
import java.util.StringJoiner;

public class EUIRM
{
    public static final String ID = "extendedui";
    public static Images images = new Images();
    public static Strings strings;
    protected static final HashMap<String, Texture> internalTextures = new HashMap<>();
    private static final HashMap<String, Texture> localTextures = new HashMap<>();

    public static void initialize() {
        strings = new Strings();
    }

    public static Texture getTexture(String path) {
        return getTexture(path, true);
    }

    public static Texture getTexture(String path, boolean useMipMap) {
        return getTexture(path, useMipMap, false, false);
    }

    public static Texture getTexture(String path, boolean useMipMap, boolean refresh, boolean suppressError) {
        Texture texture = internalTextures.get(path);
        if (texture == null || refresh) {
            texture = loadTextureImpl(Gdx.files.internal(path), useMipMap, suppressError);
            internalTextures.put(path, texture);
        }

        return texture;
    }

    public static Texture getLocalTexture(String path) {
        return getLocalTexture(path, true);
    }

    public static Texture getLocalTexture(String path, boolean useMipMap) {
        return getLocalTexture(path, useMipMap, false, false);
    }

    public static Texture getLocalTexture(String path, boolean useMipMap, boolean refresh, boolean suppressError) {
        Texture texture = localTextures.get(path);
        if (texture == null || refresh) {
            texture = loadTextureImpl(Gdx.files.local(path), useMipMap, suppressError);
            localTextures.put(path, texture);
        }

        return texture;
    }

    private static Texture loadTextureImpl(FileHandle file, boolean useMipMap, boolean suppressError) {
        if (file.exists()) {
            Texture texture = new Texture(file, useMipMap);
            if (useMipMap) {
                texture.setFilter(Texture.TextureFilter.MipMapLinearLinear, Texture.TextureFilter.Linear);
            } else {
                texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
            }
            return texture;
        }
        else {
            if (suppressError)
            {
                EUIUtils.logInfoIfDebug(EUIRM.class, "Texture does not exist: " + file.path());
            }
            else
            {
                EUIUtils.logError(EUIRM.class, "Texture does not exist: " + file.path());
            }

        }
        return null;
    }

    public static String getID(String suffix)
    {
        return ID + ":" + suffix;
    }

    private static UIStrings getUIStrings(String suffix)
    {
        return CardCrawlGame.languagePack.getUIString(getID(suffix));
    }

    public static class Images {
        public final TextureCache baseBadge = new TextureCache("images/extendedui/tooltip/Base_Badge.png");
        public final TextureCache baseBorder = new TextureCache("images/extendedui/tooltip/Base_Border.png");
        public final TextureCache arrow = new TextureCache("images/extendedui/ui/Arrow.png");
        public final TextureCache border = new TextureCache("images/extendedui/ui/Border.png");
        public final TextureCache cardPool = new TextureCache("images/extendedui/ui/CardPool.png");
        public final TextureCache cardpoolPride = new TextureCache("images/extendedui/ui/CardPool2.png");
        public final TextureCache divider = new TextureCache("images/extendedui/ui/Divider.png");
        public final TextureCache draggable = new TextureCache("images/extendedui/ui/Draggable.png");
        public final TextureCache fileSelectButton = new TextureCache("images/extendedui/ui/FileSelectButton.png");
        public final TextureCache fullSquare = new TextureCache("images/extendedui/ui/FullSquare.png");
        public final TextureCache hexagonalButton = new TextureCache("images/extendedui/ui/HexagonalButton.png");
        public final TextureCache hexagonalButtonBorder = new TextureCache("images/extendedui/ui/HexagonalButtonBorder.png");
        public final TextureCache hexagonalButtonHover = new TextureCache("images/extendedui/ui/HexagonalButtonHover.png");
        public final TextureCache longButton = new TextureCache("images/extendedui/ui/LongButton.png");
        public final TextureCache longButtonBorder = new TextureCache("images/extendedui/ui/LongButtonBorder.png");
        public final TextureCache minus = new TextureCache("images/extendedui/ui/Minus.png");
        public final TextureCache panel = new TextureCache("images/extendedui/ui/Panel.png");
        public final TextureCache panelElliptical = new TextureCache("images/extendedui/ui/Panel_Elliptical.png");
        public final TextureCache panelEllipticalHalfH = new TextureCache("images/extendedui/ui/Panel_Elliptical_Half_H.png");
        public final TextureCache panelLarge = new TextureCache("images/extendedui/ui/Panel_Large.png");
        public final TextureCache panelRounded = new TextureCache("images/extendedui/ui/Panel_Rounded.png");
        public final TextureCache panelRoundedHalfH = new TextureCache("images/extendedui/ui/Panel_Rounded_Half_H.png");
        public final TextureCache plus = new TextureCache("images/extendedui/ui/Plus.png");
        public final TextureCache rectangularButton = new TextureCache("images/extendedui/ui/RectangularButton.png");
        public final TextureCache squaredButton = new TextureCache("images/extendedui/ui/SquaredButton.png");
        public final TextureCache tag = new TextureCache("images/extendedui/ui/Tag.png");
        public final TextureCache x = new TextureCache("images/extendedui/ui/X.png");
    }

    public static class Strings {
        private final UIStrings stringsConfig = getUIStrings("config");
        private final UIStrings stringsGrammar = getUIStrings("grammar");
        private final UIStrings stringsHotkeys = getUIStrings("hotkeys");
        private final UIStrings stringsMisc = getUIStrings("misc");
        private final UIStrings stringsUIPool = getUIStrings("ui_pool");
        private final UIStrings stringsUIFilter = getUIStrings("ui_filters");


        public final String config_useVanillaCompendium = stringsConfig.TEXT[0];
        public final String config_disableCompendiumButton = stringsConfig.TEXT[1];
        public final String config_disableDescriptionIcons = stringsConfig.TEXT[2];
        public final String config_disableEffekseer = stringsConfig.TEXT[3];
        public final String config_flushOnGameStart = stringsConfig.TEXT[4];
        public final String config_flushOnRoomStart = stringsConfig.TEXT[5];
        public final String config_showModSettings = stringsConfig.TEXT[6];
        public final String config_useSeparateFonts = stringsConfig.TEXT[7];
        public final String config_overrideGameFont = stringsConfig.TEXT[8];
        public final String config_mainFont = stringsConfig.TEXT[9];
        public final String config_cardDescFont = stringsConfig.TEXT[10];
        public final String config_cardTitleFont = stringsConfig.TEXT[11];
        public final String config_tipDescFont = stringsConfig.TEXT[12];
        public final String config_tipTitleFont = stringsConfig.TEXT[13];
        public final String config_buttonFont = stringsConfig.TEXT[14];
        public final String config_energyFont = stringsConfig.TEXT[15];
        public final String config_bannerFont = stringsConfig.TEXT[16];
        public final String config_enableDebug = stringsConfig.TEXT[17];
        public final String config_reenableTooltips = stringsConfig.TEXT[18];

        public final String hotkey_cycle = stringsHotkeys.TEXT[0];
        public final String hotkey_openCardPool = stringsHotkeys.TEXT[1];
        public final String hotkey_toggle = stringsHotkeys.TEXT[2];

        public final String misc_KeyToCycle = stringsMisc.TEXT[0];
        public final String misc_TypeToSearch = stringsMisc.TEXT[1];
        public final String misc_sortByCount = stringsMisc.TEXT[2];
        public final String misc_clear = stringsMisc.TEXT[3];
        public final String misc_extraSettings = stringsMisc.TEXT[4];
        public final String misc_effekseerSettings = stringsMisc.TEXT[5];
        public final String misc_fontSettings = stringsMisc.TEXT[6];
        public final String misc_restartRequired = stringsMisc.TEXT[7];
        public final String misc_fontDescription = stringsMisc.TEXT[8];
        public final String misc_fontSeparateDescription = stringsMisc.TEXT[9];
        public final String misc_modSettingsDescription = stringsMisc.TEXT[10];
        public final String misc_compendiumButtonDescription = stringsMisc.TEXT[11];
        public final String misc_vanillaLibraryDescription = stringsMisc.TEXT[12];
        public final String misc_disableDescriptionIconDescription = stringsMisc.TEXT[13];
        public final String misc_disableEffekseerDescription = stringsMisc.TEXT[14];
        public final String misc_flushEffekseerDescription = stringsMisc.TEXT[15];
        public final String misc_keywordInstructions = stringsMisc.TEXT[16];
        public final String misc_debugDescription = stringsMisc.TEXT[17];
        public final String misc_reenableTooltips = stringsMisc.TEXT[18];

        public final String uipool_viewPool = stringsUIPool.TEXT[0];
        public final String uipool_viewPoolDescription = stringsUIPool.TEXT[1];
        public final String uipool_viewCardPool = stringsUIPool.TEXT[2];
        public final String uipool_viewRelicPool = stringsUIPool.TEXT[3];
        public final String uipool_viewPotionPool = stringsUIPool.TEXT[4];
        public final String uipool_showColorless = stringsUIPool.TEXT[5];
        public final String uipool_enlarge = stringsUIPool.TEXT[6];
        public final String uipool_addToHand = stringsUIPool.TEXT[7];
        public final String uipool_addToDeck = stringsUIPool.TEXT[8];
        public final String uipool_obtainRelic = stringsUIPool.TEXT[9];
        public final String uipool_obtainPotion = stringsUIPool.TEXT[10];

        public final String uiItemsselected = stringsUIFilter.TEXT[0];
        public final String uiKeywords = stringsUIFilter.TEXT[1];
        public final String uiAmount = stringsUIFilter.TEXT[2];
        public final String uiOrigins = stringsUIFilter.TEXT[3];
        public final String uiColors = stringsUIFilter.TEXT[4];
        public final String uiTotal = stringsUIFilter.TEXT[5];
        public final String uiFilters = stringsUIFilter.TEXT[6];
        public final String uiAny = stringsUIFilter.TEXT[7];
        public final String uiNomatch = stringsUIFilter.TEXT[8];
        public final String uiBasegame = stringsUIFilter.TEXT[9];
        public final String uiNamesearch = stringsUIFilter.TEXT[10];
        public final String uiDescriptionsearch = stringsUIFilter.TEXT[11];
        public final String uiBasic = stringsUIFilter.TEXT[12];
        public final String uiSeen = stringsUIFilter.TEXT[13];
        public final String uiUnseen = stringsUIFilter.TEXT[14];
        public final String na = stringsUIFilter.TEXT[15];
        public final String uiDisableTooltip = stringsUIFilter.TEXT[16];
        public final String uiEnableTooltip = stringsUIFilter.TEXT[17];

        public final String keyToCycle(String keyName) {
            return EUIUtils.format(misc_KeyToCycle, keyName);
        }
        public final String sortBy(String item) {
            return EUIUtils.format(misc_sortByCount, item);
        }

        // e.g. English: Red Card -> 0 1, Spanish: Carta roja -> 1 0
        public final String adjNoun(Object adj, Object noun) {
            return EUIUtils.format(stringsGrammar.TEXT[0], adj, noun);
        }

        // e.g. English: Two Cards -> 0 1, Spanish: Dos cartas -> 0 1
        public final String numNoun(Object verb, Object noun) {
            return EUIUtils.format(stringsGrammar.TEXT[1], verb, noun);
        }

        // e.g. English: Discard Cards -> 0 1, Spanish: Descarta cartas -> 0 1
        public final String verbNoun(Object verb, Object noun) {
            return EUIUtils.format(stringsGrammar.TEXT[2], verb, noun);
        }

        // e.g. English: Cards discarded -> 0 1, Spanish: Cartas descartada -> 0 1
        public final String nounVerb(Object verb, Object noun) {
            return EUIUtils.format(stringsGrammar.TEXT[3], verb, noun);
        }

        // e.g. English: Card #2 -> 0 1, Spanish: Carta #2 -> 0 1
        public final String generic2(Object noun, Object number) {
            return EUIUtils.format(stringsGrammar.TEXT[4], noun, number);
        }

        // e.g. English: Two Red Cards -> 0 1 2, Spanish: Dos Cartas rojas -> 0 2 1
        public final String numAdjNoun(Object num, Object adj, Object noun) {
            return EUIUtils.format(stringsGrammar.TEXT[5], num, adj, noun);
        }

        // e.g. English: Two Cards In Hand, Spanish: Dos cartas en la mano
        public final String numNounPlace(Object num, Object noun, Object place) {
            return EUIUtils.format(stringsGrammar.TEXT[6], num, noun, place);
        }

        // e.g. English: Discard Red Cards, Spanish: Descarta cartas rojas
        public final String verbAdjNoun(Object verb, Object adj, Object noun) {
            return EUIUtils.format(stringsGrammar.TEXT[7], verb, adj, noun);
        }

        // e.g. English: Discard Two Cards, Spanish: Descarta dos cartas
        public final String verbNumNoun(Object verb, Object num, Object noun) {
            return EUIUtils.format(stringsGrammar.TEXT[8], verb, num, noun);
        }

        // e.g. English: Discard the Cards Recklessly -> 0 1 2, Spanish: Descarta imprudentemente las cartas -> 0 2 1
        public final String verbNounAdv(Object verb, Object adj, Object noun) {
            return EUIUtils.format(stringsGrammar.TEXT[9], verb, adj, noun);
        }

        // e.g. English: Two Red Cards In Hand, Spanish: Dos cartas rojas en la mano
        public final String numAdjNounPlace(Object num, Object adj, Object noun, Object place) {
            return EUIUtils.format(stringsGrammar.TEXT[10], num, adj, noun, place);
        }

        // e.g. English: Discard Two Cards In Hand, Spanish: Descarta dos cartas en la mano
        public final String verbNumNounPlace(Object num, Object adj, Object noun, Object place) {
            return EUIUtils.format(stringsGrammar.TEXT[11], num, adj, noun, place);
        }

        // e.g. English: Discard Two Red Cards In Hand, Spanish: Descarta dos cartas rojas en la mano
        public final String verbNumAdjNounPlace(Object verb, Object num, Object adj, Object noun, Object place) {
            return EUIUtils.format(stringsGrammar.TEXT[12], verb, num, adj, noun, place);
        }

        // e.g. English: O1 and O2, Spanish: O1 y O2
        public final String and(Object obj1, Object obj2) {return EUIUtils.format(stringsGrammar.TEXT[13], obj1, obj2);}

        // e.g. English: O1 or O2, Spanish: O1 o O2
        public final String or(Object obj1, Object obj2) {return EUIUtils.format(stringsGrammar.TEXT[14], obj1, obj2);}

        // e.g. English: Not O1, Spanish: No 01
        public final String not(Object obj1) {return EUIUtils.format(stringsGrammar.TEXT[15], obj1);}

        // e.g. English: Card -> Cards, Spanish: Carta -> Cartas
        public final String plural(Object obj) {
            String base = String.valueOf(obj);
            return EUIUtils.format(stringsGrammar.EXTRA_TEXT[0], obj);
        }

        // e.g. English: Card -> Card(s)
        public final String pluralC(Object obj) {return EUIUtils.format(stringsGrammar.EXTRA_TEXT[1], obj);}

        // e.g. English: Discard -> Discarded, Spanish: Descarta -> Descartada
        public final String past(Object obj) {return EUIUtils.format(stringsGrammar.EXTRA_TEXT[2], obj);}

        public final String present(Object obj) {return EUIUtils.format(stringsGrammar.EXTRA_TEXT[3], obj);}

        public final String joinWithAnd(List<String> values) {
            return joinWith(this::and, values);
        }
        public final String joinWithAnd(String... values) {
            return joinWith(this::and, values);
        }
        public final String joinWithOr(List<String> values) {
            return joinWith(this::or, values);
        }
        public final String joinWithOr(String... values) {
            return joinWith(this::or, values);
        }

        public final String joinWith(FuncT2<String, String, String> strFunc, List<String> values) {
            if (values.size() == 0) {
                return "";
            }
            if (values.size() == 1) {
                return values.get(0);
            }
            StringJoiner sj = new StringJoiner(", ");

            int i;
            for (i = 0; i < values.size() - 1; i++) {
                sj.add(values.get(i));
            }

            return strFunc.invoke(sj.toString(), values.get(i));
        }
        public final String joinWith(FuncT2<String, String, String> strFunc, String... values) {
            if (values.length == 0) {
                return "";
            }
            if (values.length == 1) {
                return values[0];
            }
            StringJoiner sj = new StringJoiner(", ");
            int var4 = values.length;

            int i;
            for (i = 0; i < values.length - 1; i++) {
                sj.add(values[i]);
            }

            return strFunc.invoke(sj.toString(), values[i]);
        }
    }
}
