package com.github.tsuoihito.commandbook;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BookHandler {
    public static Optional<List<String>> getCommands(ItemStack itemStack) {
        return getBookMeta(itemStack)
                .map(BookMeta::getPages)
                .map(pages -> String.join("\n", pages))
                .map(text -> Arrays.asList(text.split("\n")))
                .filter(BookHandler::isCommandBookLines)
                .map(BookHandler::getCommandLines);
    }

    private static List<String> getCommandLines(List<String> lines) {
        return lines
                .subList(1, lines.size())
                .stream()
                .filter(line -> !line.startsWith("#"))
                .collect(Collectors.toList());
    }

    private static boolean isCommandBookLines(List<String> lines) {
        return lines.size() > 0 && lines.get(0).equalsIgnoreCase("[CommandBook]");
    }

    private static Optional<BookMeta> getBookMeta(ItemStack itemStack) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        if (isWritableBook(itemStack) && itemMeta != null) {
            return Optional.of((BookMeta) itemMeta);
        }
        return Optional.empty();
    }

    private static boolean isWritableBook(ItemStack itemStack) {
        return itemStack.getType().equals(Material.WRITABLE_BOOK);
    }
}
